package com.event.eventsync.services;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.mappers.EventFeedbackMapper;
import com.event.eventsync.mappers.EventMapper;
import com.event.eventsync.repositories.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventSentimentService eventSentimentService;
    private final EventMapper eventMapper;
    private final EventFeedbackMapper eventFeedbackMapper;

    public void addEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        eventRepository.save(event);
    }
    public List<EventDTO> getEvents() {
        List<EventDTO> eventDTOList = eventRepository.findAllEventsWithFeedbackAndSentiments()
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
        return eventDTOList;
    }

    public void addEventFeedback(Integer eventId, EventFeedbackDTO eventFeedbackDTO) throws JsonProcessingException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")); // very basic exception for set up and testing purposes now
        EventSentiment eventSentiment = getEventSentiment(eventFeedbackDTO.feedback());
        EventFeedback eventFeedback = eventFeedbackMapper.toEntity(eventFeedbackDTO);
        event.addFeedback(eventFeedback);
        eventFeedback.addEventSentiment(eventSentiment);
        eventRepository.save(event);
    }

    public EventSentiment getEventSentiment(String feedback) throws JsonProcessingException {
        return eventSentimentService.getEventSentiment(feedback);
    }
    public Map<String, Long> getEventSummary(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found"));

        Map<String, Long> sentimentsMap = Stream.of("Positive", "Neutral", "Negative")
                .collect(Collectors.toMap(
                        label -> label,
                        label -> event.getEventFeedbackList().stream()
                                .filter(f -> f.getSentiment() != null)
                                .filter(f -> f.getSentiment().getLabel().equals(label))
                                .count()
                ));

        return sentimentsMap;
    }
}
