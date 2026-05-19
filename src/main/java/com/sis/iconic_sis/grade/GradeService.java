package com.sis.iconic_sis.grade;

import org.springframework.stereotype.Service;

@Service
public class GradeService {
    public GradeRepository gradeRepo;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepo = gradeRepository;
    }

    public void addGrade(Grade grade) {
        gradeRepo.save(grade);
    }

    public void deleteGrade(Integer id) {
        gradeRepo.deleteById(id);
    }

    public void updateGrade(Integer id, Grade grade) {
        Grade current = gradeRepo.findById(id).get();
        current.setName(grade.getName());
        gradeRepo.save(current);
    }

    public Grade getGradeByName(String gradeName) {
        return gradeRepo.findByName(gradeName).get();
    }
}
