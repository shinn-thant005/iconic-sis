package com.sis.iconic_sis.grade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grade")
public class GradeController {
    public GradeService gradeService;
    public GradeRepository gradeRepo;

    public GradeController(GradeService gradeService, GradeRepository gradeRepo) {
        this.gradeService = gradeService;
        this.gradeRepo = gradeRepo;
    }

    @GetMapping
    public List<Grade> getGrades() {
        return gradeRepo.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addGrade(@RequestBody Grade grade) {
        gradeService.addGrade(grade);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable("id") Integer id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{gradeId}")
    public ResponseEntity<Grade> updateGrade(@PathVariable("gradeId") Integer gradeId, @RequestBody Grade grade) {
        gradeService.updateGrade(gradeId, grade);
        return ResponseEntity.noContent().build();
    }
}
