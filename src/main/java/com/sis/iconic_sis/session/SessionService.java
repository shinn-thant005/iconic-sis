package com.sis.iconic_sis.session;

import com.sis.iconic_sis.grade.Grade;
import com.sis.iconic_sis.grade.GradeService;
import com.sis.iconic_sis.room.Room;
import com.sis.iconic_sis.room.RoomService;
import com.sis.iconic_sis.session.dto.SessionCreationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepo;
    private final GradeService gradeService;
    private final RoomService roomService;

    public SessionService(SessionRepository sessionRepository,  GradeService gradeService,  RoomService roomService) {
        this.sessionRepo = sessionRepository;
        this.gradeService = gradeService;
        this.roomService = roomService;
    }

    public List<Session> getAll() {
        return sessionRepo.findAll();
    }

    public void addNewSession(SessionCreationDTO session) {
        Session newSession = new Session();
        Grade grade = gradeService.getGradeByName(session.grade());
        Room room = roomService.getRoomByName(session.room());

        newSession.setId(session.sessionId());
        newSession.setName(session.sessionName());
        newSession.setGrade(grade);
        newSession.setRoom(room);
        newSession.setCapacity(session.capacity());

        sessionRepo.save(newSession);
    }

    public Session getSessionById(String id) {
        return sessionRepo.findById(id).get();
    }
}
