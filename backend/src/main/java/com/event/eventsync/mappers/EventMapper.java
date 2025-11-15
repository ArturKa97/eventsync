package com.event.eventsync.mappers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.entities.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventFeedbackMapper.class})
public interface EventMapper {

    EventDTO toDTO(Event event);

    Event toEntity(EventDTO eventDTO);

}
