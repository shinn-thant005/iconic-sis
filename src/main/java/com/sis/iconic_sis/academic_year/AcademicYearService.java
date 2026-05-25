package com.sis.iconic_sis.academic_year;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicYearService {
    private final AcademicYearRepository academicYearRepo;

    public AcademicYearService(AcademicYearRepository academicYearRepo) {
        this.academicYearRepo = academicYearRepo;
    }

    public List<AcademicYear> getAcademicYears() {
        return academicYearRepo.findAll();
    }

    public void addAcademicYear(AcademicYear academicYear) {
        AcademicYear currentYear = new AcademicYear(academicYear.getYear());
        academicYearRepo.save(currentYear);
    }

    public void deleteAcademicYearById(Integer id) {
        academicYearRepo.deleteById(id);
    }

    public AcademicYear getAcademicByYear(String year) {
        return academicYearRepo.getAcademicByYear(year).get();
    }
}
