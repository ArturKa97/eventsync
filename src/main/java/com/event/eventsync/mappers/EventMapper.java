package com.event.eventsync.mappers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventFeedbackMapper.class})
public interface EventMapper {

    @Mapping(target = "eventFeedbackList", source = "event.eventFeedbackList")
    EventDTO toDTO(Event event);
    @Mapping(target = "eventFeedbackList", source = "eventDTO.eventFeedbackList")
    Event toEntity(EventDTO eventDTO);

}
