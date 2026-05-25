package com.sis.iconic_sis.student.dto;

import com.sis.iconic_sis.student.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentDetailDTO(
        String profileUrl,
        Long studentId,
        String name,
        Student.Gender gender,
        LocalDate birthDate,
        String studentNrc,

        String grade,
        String assignedSession,
        Student.Elective elective,
        String academicYear,
        Student.ResidentialStatus residentialStatus,


        String homeAddress,
        String city,
        String state,
        String phoneNumber,
        String email,
        String previousSchool,

        String fatherName,
        String fatherNrc,
        String fatherJob,
        String fatherPhone,
        String motherName,
        String motherNrc,
        String motherJob,
        String motherPhone,
        LocalDateTime startingDate
) {
}
