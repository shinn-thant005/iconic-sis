package com.sis.iconic_sis.student.mapper;

import com.sis.iconic_sis.student.Student;
import com.sis.iconic_sis.student.dto.StudentCreationDTO;
import com.sis.iconic_sis.student.dto.StudentDetailDTO;
import com.sis.iconic_sis.student.dto.StudentEditDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "academicYear", ignore = true)
    @Mapping(target = "phoneContact", ignore = true)
    @Mapping(target = "father", ignore = true)
    @Mapping(target = "mother", ignore = true)
    void updateStudentFromDto(StudentEditDTO dto, @MappingTarget  Student student);

    @Mapping(target = "id", source = "studentId") // Explicitly map studentId to id
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "academicYear", ignore = true)
    @Mapping(target = "phoneContact", ignore = true)
    @Mapping(target = "father", ignore = true)
    @Mapping(target = "mother", ignore = true)
    @Mapping(target = "profileImageUrl", ignore = true)
    Student createStudentFromDto(StudentCreationDTO dto);

    @Mapping(source = "grade.name", target = "grade", defaultValue = "Unassigned")
    @Mapping(source = "session.name", target = "assignedSession", defaultValue = "Unassigned")
    @Mapping(source = "academicYear.year", target = "academicYear", defaultValue = "Unassigned")

    // Map Student Contact
    @Mapping(source = "phoneContact.phoneNumber", target = "phoneNumber")

    // Map Father
    @Mapping(source = "father.name", target = "fatherName")
    @Mapping(source = "father.nrc", target = "fatherNrc")
    @Mapping(source = "father.job", target = "fatherJob")
    @Mapping(source = "father.phoneContact.phoneNumber", target = "fatherPhone")

    // Map Mother
    @Mapping(source = "mother.name", target = "motherName")
    @Mapping(source = "mother.nrc", target = "motherNrc")
    @Mapping(source = "mother.job", target = "motherJob")
    @Mapping(source = "mother.phoneContact.phoneNumber", target = "motherPhone")
    StudentDetailDTO toDetailDto(Student s);
}
