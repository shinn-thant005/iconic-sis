package com.sis.iconic_sis.student;

import com.sis.iconic_sis.student.dto.StudentSummaryDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.sis.iconic_sis.student.dto.StudentSummaryDTO(" +
            "s.profileImageUrl, s.name, s.id, g.name, ses.name) " +
            "FROM Student s " +
            "LEFT JOIN s.grade g " +
            "LEFT JOIN s.session ses")
    List<StudentSummaryDTO> findAllStudentSummaries();

    // 2. JOIN FETCH: Highly optimized for the Detail view.
    // Fetches the Student and ALL related nested tables in exactly ONE SQL query.
    @Query("SELECT s FROM Student s " +
            "LEFT JOIN FETCH s.grade " +
            "LEFT JOIN FETCH s.session " +
            "LEFT JOIN FETCH s.academicYear " +
            "LEFT JOIN FETCH s.phoneContact " +
            "LEFT JOIN FETCH s.father f " +
            "LEFT JOIN FETCH f.phoneContact " +
            "LEFT JOIN FETCH s.mother m " +
            "LEFT JOIN FETCH m.phoneContact " +
            "WHERE s.id = :id")
    Optional<Student> findByIdWithFullDetails(@Param("id") Long id);


}
