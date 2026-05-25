package com.sis.iconic_sis.session.dto;

public record SessionCreationDTO(
        String sessionId,
        String sessionName,
        String grade,
        String room,
        Integer capacity
) {
}
