package com.event.eventsync.dtos;

import lombok.Builder;

@Builder
public record EventSentimentDTO(
        Integer id,
        String label,
        Double score
) {

}
