package com.sis.iconic_sis.grade;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Transactional
    void deleteByName(String name);

    @Transactional
    Optional<Grade> findByName(String name);
}
