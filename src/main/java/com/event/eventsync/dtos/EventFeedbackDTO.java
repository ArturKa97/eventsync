package com.event.eventsync.dtos;

import lombok.Builder;

@Builder
public record EventFeedbackDTO(
        Long id,
        String feedback
) {

}
