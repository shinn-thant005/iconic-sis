package com.sis.iconic_sis.parent;

import com.sis.iconic_sis.student.Student;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parent")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/parents")
    public List<Parent> findAllParent() {
        return parentService.findAllParent();
    }
}
