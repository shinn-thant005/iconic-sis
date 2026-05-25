package com.sis.iconic_sis.session;

import com.sis.iconic_sis.session.dto.SessionCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/get")
    public List<Session> getAllSessions() {
        return sessionService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Session> addSession(@RequestBody SessionCreationDTO session) {
        sessionService.addNewSession(session);
        return ResponseEntity.created(null).build();
    }
}
