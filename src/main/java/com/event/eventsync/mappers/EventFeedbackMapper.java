package com.event.eventsync.mappers;

import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.entities.EventFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventSentimentMapper.class})
public interface EventFeedbackMapper {

    @Mapping(target = "eventSentimentDTO", source = "eventFeedback.sentiment")
    EventFeedbackDTO toDTO(EventFeedback eventFeedback);
    @Mapping(target = "sentiment", source = "eventFeedbackDTO.eventSentimentDTO")
    EventFeedback toEntity(EventFeedbackDTO eventFeedbackDTO);

}
