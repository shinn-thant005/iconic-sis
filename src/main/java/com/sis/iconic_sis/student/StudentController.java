package com.sis.iconic_sis.student;

import com.sis.iconic_sis.student.dto.StudentCreationDTO;
import com.sis.iconic_sis.student.dto.StudentDetailDTO;
import com.sis.iconic_sis.student.dto.StudentEditDTO;
import com.sis.iconic_sis.student.dto.StudentSummaryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody StudentCreationDTO studentCreationDTO) {
        studentService.addStudent(studentCreationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/upload-img")
    public ResponseEntity<Object> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        studentService.saveStudentImage(id, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete-img")
    public ResponseEntity<Object> deleteStudentImage(@PathVariable Long id) {
        studentService.deleteStudentImage(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<StudentSummaryDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudentSummaries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetailDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentDetailById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudentById(@PathVariable Long id, @RequestBody StudentEditDTO dto) {
        studentService.updateStudent(id, dto);
        return ResponseEntity.noContent().build();
    }

}
