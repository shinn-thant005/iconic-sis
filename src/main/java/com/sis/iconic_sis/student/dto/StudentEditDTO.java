package com.sis.iconic_sis.student.dto;

import com.sis.iconic_sis.student.Student;
import java.time.LocalDate;

public record StudentEditDTO(
        String name,
        Student.Gender gender,
        LocalDate birthDate,
        String studentNrc,

        // --- USING IDs INSTEAD OF STRINGS ---
        Integer gradeId,
        String sessionId,
        Integer academicYearId,

        Student.Elective elective,
        Student.ResidentialStatus residentialStatus,
        String previousSchool,

        String phoneNumber,
        String homeAddress,
        String city,
        String state,
        String email,

        String fatherName,
        String fatherNrc,
        String fatherJob,
        String fatherPhone,

        String motherName,
        String motherNrc,
        String motherJob,
        String motherPhone
) {}