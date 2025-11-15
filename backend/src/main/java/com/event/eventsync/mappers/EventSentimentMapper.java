package com.event.eventsync.mappers;

import com.event.eventsync.dtos.EventSentimentDTO;
import com.event.eventsync.entities.EventSentiment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventSentimentMapper {

    EventSentimentDTO toDTO(EventSentiment eventSentiment);
    EventSentiment toEntity(EventSentimentDTO eventSentimentDTO);

}
