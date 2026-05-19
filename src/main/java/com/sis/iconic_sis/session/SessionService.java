package com.sis.iconic_sis.session;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    public void addNewSession(Session session) {
        sessionRepository.save(session);
    }

    public Session getSessionById(String id) {
        return sessionRepository.findById(id).get();
    }
}
