package com.event.eventsync.services;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.entities.Event;
import com.event.eventsync.mappers.EventMapper;
import com.event.eventsync.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventMapper eventMapper;
    @InjectMocks
    private EventService eventService;

    @Test
    public void EventService_GetEventById_ShouldReturnEventDTO() {
        //Given
        Event event = Event.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();
        EventDTO eventDTO = EventDTO.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();

        when(eventRepository.getEventById(event.getId())).thenReturn(Optional.of(event));
        when(eventMapper.toDTO(event)).thenReturn(eventDTO);
        //When
        EventDTO result = eventService.getEventById(event.getId());
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(eventDTO);
        verify(eventRepository).getEventById(event.getId());
        verify(eventMapper).toDTO(event);
    }

}