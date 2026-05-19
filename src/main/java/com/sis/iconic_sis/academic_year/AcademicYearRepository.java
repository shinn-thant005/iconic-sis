package com.sis.iconic_sis.academic_year;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Integer> {
    Optional<AcademicYear> getAcademicByName(String academicYearName);
}
