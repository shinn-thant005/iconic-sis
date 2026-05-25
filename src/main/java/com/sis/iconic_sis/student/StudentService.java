package com.sis.iconic_sis.student;

import com.sis.iconic_sis.academic_year.AcademicYear;
import com.sis.iconic_sis.academic_year.AcademicYearRepository;
import com.sis.iconic_sis.academic_year.AcademicYearService;
import com.sis.iconic_sis.grade.Grade;
import com.sis.iconic_sis.grade.GradeRepository;
import com.sis.iconic_sis.grade.GradeService;
import com.sis.iconic_sis.parent.Parent;
import com.sis.iconic_sis.parent.ParentService;
import com.sis.iconic_sis.phone_contact.PhoneContact;
import com.sis.iconic_sis.phone_contact.PhoneContactRepository;
import com.sis.iconic_sis.phone_contact.PhoneContactService;
import com.sis.iconic_sis.session.Session;
import com.sis.iconic_sis.session.SessionRepository;
import com.sis.iconic_sis.session.SessionService;
import com.sis.iconic_sis.storage.CloudStorageService;
import com.sis.iconic_sis.student.dto.StudentCreationDTO;
import com.sis.iconic_sis.student.dto.StudentDetailDTO;
import com.sis.iconic_sis.student.dto.StudentEditDTO;
import com.sis.iconic_sis.student.dto.StudentSummaryDTO;
import com.sis.iconic_sis.student.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    private final GradeService gradeService;
    private final AcademicYearService academicYearService;
    private final PhoneContactService phoneContactService;
    private final ParentService parentService;
    private final SessionService sessionService;
    private final CloudStorageService cloudStorageService;
    private final StudentRepository studentRepo;

    private final GradeRepository gradeRepo;
    private final SessionRepository sessionRepo;
    private final AcademicYearRepository academicYearRepo;
    private final StudentMapper studentMapper;
    private final PhoneContactRepository phoneContactRepo;



    public StudentService(
            GradeService gradeService,
            AcademicYearService academicYearService,
            PhoneContactService phoneContactService,
            ParentService parentService,
            SessionService sessionService,
            StudentRepository studentRepo,
            CloudStorageService cloudStorageService,
            GradeRepository gradeRepo,
            SessionRepository sessionRepo,
            AcademicYearRepository academicYearRepo,
            StudentMapper studentMapper,
            PhoneContactRepository phoneContactRepo) {

        this.gradeService = gradeService;
        this.academicYearService = academicYearService;
        this.phoneContactService = phoneContactService;
        this.parentService = parentService;
        this.sessionService = sessionService;
        this.studentRepo = studentRepo;
        this.cloudStorageService = cloudStorageService;
        this.gradeRepo = gradeRepo;
        this.sessionRepo = sessionRepo;
        this.academicYearRepo = academicYearRepo;
        this.studentMapper = studentMapper;
        this.phoneContactRepo = phoneContactRepo;
    }


    @Transactional
    public void addStudent(StudentCreationDTO student) {
        // 1. Proactive Duplicate Check
        if (studentRepo.existsById(student.studentId())) {
            throw new RuntimeException("DuplicateEntry: A student with ID 'ICN-" + student.studentId() + "' already exists.");
        }

        // 2. MapStruct creates the base entity
        Student newStudent = studentMapper.createStudentFromDto(student);

        // 3. Attach Proxies (Zero DB Lookups!)
        if(student.gradeId() != null) newStudent.setGrade(gradeRepo.getReferenceById(student.gradeId()));
        if(student.sessionId() != null) newStudent.setSession(sessionRepo.getReferenceById(student.sessionId()));
        if(student.academicYearId() != null) newStudent.setAcademicYear(academicYearRepo.getReferenceById(student.academicYearId()));

        // 4. Create and Attach Nested Relationships
        PhoneContact studentPhoneContact =  phoneContactService.createPhoneContact(student.name(), student.phoneNumber(), PhoneContact.Role.STUDENT);
        newStudent.setPhoneContact(studentPhoneContact);

        Parent father = parentService.addParent(
                student.fatherName(),
                student.fatherNrc(),
                Parent.ParentRole.FATHER,
                student.fatherJob(),
                student.fatherPhone()
        );
        newStudent.setFather(father);

        Parent mother = parentService.addParent(
                student.motherName(),
                student.motherNrc(),
                Parent.ParentRole.MOTHER,
                student.motherJob(),
                student.motherPhone()
        );
        newStudent.setMother(mother);

        // 5. System-managed defaults
        newStudent.setDurationInIconic(1);
        newStudent.setStartingDate(LocalDateTime.now());

        // 6. Final Save
        studentRepo.save(newStudent);

    }

    @Transactional
    public void saveStudentImage(Long studentId, MultipartFile file) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String uploadedUrl = cloudStorageService.uploadImage(file);

        student.setProfileImageUrl(uploadedUrl);
        studentRepo.save(student);
    }

    @Transactional
    public void deleteStudentImage(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (student.getProfileImageUrl() != null) {
            cloudStorageService.deleteImage(student.getProfileImageUrl()); // Delete from Supabase
            student.setProfileImageUrl(null); // Clear from Database
            studentRepo.save(student);
        }
    }

    public List<StudentSummaryDTO> getAllStudentSummaries() {
        return studentRepo.findAllStudentSummaries();
    }

    public StudentDetailDTO getStudentDetailById(Long id) {
        Student s = studentRepo.findByIdWithFullDetails(id)
                .orElseThrow(() -> new RuntimeException("Student Profile not found for ID: " + id));

        return studentMapper.toDetailDto(s);
    }

    @Transactional
    public void deleteStudent(Long id) {
        deleteStudentImage(id);
        studentRepo.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, StudentEditDTO dto) {
        Student s = studentRepo.findByIdWithFullDetails(id)
                .orElseThrow(() -> new  RuntimeException("Student not found for ID: " + id));

        studentMapper.updateStudentFromDto(dto, s);

        if(dto.gradeId() != null) s.setGrade(gradeRepo.getReferenceById(dto.gradeId()));
        if (dto.sessionId() != null) s.setSession(sessionRepo.getReferenceById(dto.sessionId()));
        if (dto.academicYearId() != null) s.setAcademicYear(academicYearRepo.getReferenceById(dto.academicYearId()));

        if (s.getPhoneContact() != null) {
            s.getPhoneContact().setPhoneNumber(dto.phoneNumber());
        }

        // Handle Parent Aggregate Roots
        if (s.getFather() != null) {
            s.getFather().setName(dto.fatherName());
            s.getFather().setNrc(dto.fatherNrc());
            s.getFather().setJob(dto.fatherJob());
            if (s.getFather().getPhoneContact() != null) {
                s.getFather().getPhoneContact().setPhoneNumber(dto.fatherPhone());
            }
        }

        if (s.getMother() != null) {
            s.getMother().setName(dto.motherName());
            s.getMother().setNrc(dto.motherNrc());
            s.getMother().setJob(dto.motherJob());
            if (s.getMother().getPhoneContact() != null) {
                s.getMother().getPhoneContact().setPhoneNumber(dto.motherPhone());
            }
        }
        studentRepo.save(s);
    }

}
