package com.event.eventsync.services;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.mappers.EventFeedbackMapper;
import com.event.eventsync.mappers.EventMapper;
import com.event.eventsync.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventMapper eventMapper;
    @Mock
    private EventFeedbackMapper eventFeedbackMapper;
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

    @Test
    public void EventService_GetEventById_ShouldThrowEntityNotFoundException() {
        //Given
        Integer nonExistentID = 999999;
        when(eventRepository.getEventById(nonExistentID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            eventService.getEventById(nonExistentID);
        });
        assertEquals("Event with id [999999] not found", exception.getMessage());
    }

    @Test
    public void EventService_AddEvent() {
        //Given
        Event event = Event.builder()
                .id(2)
                .title("test")
                .description("test")
                .build();
        EventDTO eventDTO = EventDTO.builder()
                .id(2)
                .title("test")
                .description("test")
                .build();
        when(eventMapper.toEntity(eventDTO)).thenReturn(event);
        //When
        eventService.addEvent(eventDTO);
        //Then
        verify(eventMapper).toEntity(eventDTO);
        verify(eventRepository).save(event);
    }
    public void EventService_GetEvents_ShouldReturnListOfEventDTO() {
        //Given
        Event event1 = Event.builder()
                .id(1)
                .title("test1")
                .description("test1")
                .build();
        Event event2 = Event.builder()
                .id(2)
                .title("test2")
                .description("test2")
                .build();
        List<Event> events = List.of(event1, event2);
        EventDTO eventDTO1 = EventDTO.builder()
                .id(1)
                .title("test1")
                .description("test1")
                .build();
        EventDTO eventDTO2 = EventDTO.builder()
                .id(2)
                .title("test2")
                .description("test2")
                .build();
        List<EventDTO> eventsDTO = List.of(eventDTO1, eventDTO2);
        when(eventRepository.findAllEventsWithFeedbackAndSentiments()).thenReturn(events);
        when(eventMapper.toDTO(event1)).thenReturn(eventDTO1);
        when(eventMapper.toDTO(event2)).thenReturn(eventDTO2);
        //When
        List<EventDTO> result = eventService.getEvents();
        //Then
        assertThat(result).isNotEmpty()
                .isNotNull()
                .isEqualTo(eventsDTO);
        verify(eventRepository.findAllEventsWithFeedbackAndSentiments());
        verify(eventMapper).toDTO(event1);
        verify(eventMapper).toDTO(event2);

    }

    @Test
    public void EventService_GetEvents_ShouldThrowEntityNotFoundException() {
        // Given
        when(eventRepository.findAllEventsWithFeedbackAndSentiments()).thenReturn(List.of());

        // When & Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> eventService.getEvents());

        assertEquals("No Events found", exception.getMessage());
        verify(eventRepository).findAllEventsWithFeedbackAndSentiments();
        verify(eventMapper, never()).toDTO(any());
    }

    @Test
    public void EventService_DeleteEventById() {
        //Given
        Event event1 = Event.builder()
                .id(1)
                .title("test1")
                .description("test1")
                .build();
        when(eventRepository.getEventById(event1.getId())).thenReturn(Optional.of(event1));
        //When
        eventService.deleteEventById(event1.getId());
        //Then
        verify(eventRepository).getEventById(event1.getId());
        verify(eventRepository).delete(event1);
    }

    @Test
    public void EventService_DeleteEventById_ShouldThrowEntityNotFoundException() {
        //Given
        Integer nonExistentID = 999999;
        when(eventRepository.getEventById(nonExistentID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            eventService.deleteEventById(nonExistentID);
        });
        assertEquals("Event with id [999999] not found", exception.getMessage());
    }

    @Test
    public void EventService_GetEventSummary_ShouldReturnSentimentCounts() {
        // Given
        EventSentiment positive = EventSentiment.builder().label("Positive").build();
        EventSentiment neutral = EventSentiment.builder().label("Neutral").build();
        EventSentiment negative = EventSentiment.builder().label("Negative").build();

        EventFeedback feedback1 = EventFeedback.builder().sentiment(positive).build();
        EventFeedback feedback2 = EventFeedback.builder().sentiment(positive).build();
        EventFeedback feedback3 = EventFeedback.builder().sentiment(neutral).build();

        Event event1 = Event.builder()
                .id(1)
                .title("test1")
                .description("test1")
                .eventFeedbackList(List.of(feedback1, feedback2, feedback3))
                .build();

        when(eventRepository.getEventById(event1.getId())).thenReturn(Optional.of(event1));

        // When
        Map<String, Long> summary = eventService.getEventSummary(event1.getId());

        // Then
        assertEquals(2L, summary.get("Positive"));
        assertEquals(1L, summary.get("Neutral"));
        assertEquals(0L, summary.get("Negative"));

        verify(eventRepository).getEventById(event1.getId());
    }

    @Test
    public void EventService_GetEventSummary_ShouldThrowEntityNotFoundException() {
        // Given
        Integer nonExistentID = 999999;
        when(eventRepository.getEventById(nonExistentID)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> eventService.getEventSummary(nonExistentID));

        assertEquals("Event with id [999999] not found", exception.getMessage());

        verify(eventRepository).getEventById(nonExistentID);
    }


}