package com.event.eventsync.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventFeedbackDTO(
        Integer id,
        String feedback,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timeStamp,
        EventSentimentDTO eventSentimentDTO
) {

}
