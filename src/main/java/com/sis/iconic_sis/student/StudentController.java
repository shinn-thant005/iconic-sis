package com.sis.iconic_sis.student;

import com.sis.iconic_sis.student.dto.StudentCreationDTO;
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
        URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentCreationDTO.studentId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/upload-img")
    public ResponseEntity<Object> uploadImage(
            @PathVariable String id,
            @RequestParam("file") MultipartFile file) {


    }



}
