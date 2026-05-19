package com.sis.iconic_sis.student;

import com.sis.iconic_sis.academic_year.AcademicYear;
import com.sis.iconic_sis.academic_year.AcademicYearService;
import com.sis.iconic_sis.grade.Grade;
import com.sis.iconic_sis.grade.GradeService;
import com.sis.iconic_sis.parent.Parent;
import com.sis.iconic_sis.parent.ParentService;
import com.sis.iconic_sis.phone_contact.PhoneContact;
import com.sis.iconic_sis.phone_contact.PhoneContactService;
import com.sis.iconic_sis.session.Session;
import com.sis.iconic_sis.session.SessionService;
import com.sis.iconic_sis.student.dto.StudentCreationDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {
    GradeService gradeService;
    AcademicYearService academicYearService;
    PhoneContactService phoneContactService;
    ParentService parentService;
    SessionService sessionService;

    StudentRepository studentRepo;


    public StudentService(
            GradeService gradeService, AcademicYearService academicYearService,
            PhoneContactService phoneContactService, ParentService parentService,
            SessionService sessionService,  StudentRepository studentRepo) {
        this.gradeService = gradeService;
        this.academicYearService = academicYearService;
        this.phoneContactService = phoneContactService;
        this.parentService = parentService;
        this.sessionService = sessionService;
        this.studentRepo = studentRepo;
    }


    @Transactional
    public void addStudent(StudentCreationDTO student) {
        Grade grade = gradeService.getGradeByName(student.grade());
        AcademicYear academicYear = academicYearService.getAcademicByName(student.academicYear());
        PhoneContact studentPhoneContact = phoneContactService.createPhoneContact(student.name(), student.phoneNumber(), PhoneContact.Role.STUDENT);
        Session session = sessionService.getSessionById(student.assignedSession());

        Parent father = parentService.addParent(
                student.fatherName(), student.fatherNrc(), Parent.ParentRole.FATHER,
                student.fatherJob(), student.fatherPhone());

        Parent mother = parentService.addParent(
                student.motherName(), student.motherNrc(), Parent.ParentRole.MOTHER,
                student.motherJob(), student.motherPhone());

        Student newStudent = new Student(
                student.studentId(), student.name(), student.gender(), student.birthDate(),
                grade, academicYear, studentPhoneContact, father, mother, student.homeAddress(),
                student.city(), student.state(), student.email(), student.residentialStatus(),
                session, student.elective(), student.previousSchool(), 1, LocalDateTime.now()
        );

        studentRepo.save(newStudent);
    }
}
