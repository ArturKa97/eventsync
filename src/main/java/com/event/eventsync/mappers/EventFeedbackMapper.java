package com.event.eventsync.mappers;

import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.entities.EventFeedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventFeedbackMapper {

    EventFeedbackDTO toDTO(EventFeedback eventFeedback);
    EventFeedback toEntity(EventFeedbackDTO eventFeedbackDTO);

}
