package com.event.eventsync.services;

import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
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

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
    public List<Event> getEvents() {
        return eventRepository.findAllEventsWithFeedbackAndSentiments();
    }

    public void addEventFeedback(Integer eventId, EventFeedback eventFeedback) throws JsonProcessingException {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")); // very basic exception for set up and testing purposes now
        EventSentiment eventSentiment = getEventSentiment(eventFeedback.getFeedback());
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
