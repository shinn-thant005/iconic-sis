package com.sis.iconic_sis.student.dto;

public record StudentSummaryDTO(
        String profileUrl,
        String name,
        Long id,
        String grade,
        String session
) {
}
