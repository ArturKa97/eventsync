package com.event.eventsync.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record EventDTO(
        Integer id,
        String title,
        String description,
        List<EventFeedbackDTO> eventFeedbackList
) {

}
