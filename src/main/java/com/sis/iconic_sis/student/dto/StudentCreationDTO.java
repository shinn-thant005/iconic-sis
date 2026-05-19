package com.sis.iconic_sis.student.dto;

import com.sis.iconic_sis.student.Student;

import java.time.LocalDate;

public record StudentCreationDTO(
        Long studentId,
        String name,
        Student.Gender gender,
        LocalDate birthDate,
        String grade,
        String academicYear,
        String phoneNumber,
        String homeAddress,
        String city,
        String state,
        String email,
        Student.ResidentialStatus residentialStatus,
        String assignedSession,
        Student.Elective elective,
        String previousSchool,
        String fatherName,
        String fatherNrc,
        String fatherJob,
        String fatherPhone,
        String motherName,
        String motherNrc,
        String motherJob,
        String motherPhone
) {
}
