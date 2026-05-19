package com.sis.iconic_sis.academic_year;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/academic")
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    public  AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping
    public List<AcademicYear> getAcademicYears() {
        return academicYearService.getAcademicYears();
    }

    @PostMapping("/add")
    public ResponseEntity<AcademicYear> addAcademicYear(@RequestBody AcademicYear academicYear) {
        academicYearService.addAcademicYear(academicYear);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AcademicYear> deleteAcademicYear(@PathVariable("id") Integer id) {
        academicYearService.deleteAcademicYearById(id);
        return ResponseEntity.noContent().build();
    }
}
