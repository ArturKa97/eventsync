package com.event.eventsync.services;

import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.repositories.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        EventSentiment eventSentiment = getEventSentiment();
        event.addFeedback(eventFeedback);
        eventFeedback.addEventSentiment(eventSentiment);
        eventRepository.save(event);
    }

    public EventSentiment getEventSentiment() throws JsonProcessingException {
        return eventSentimentService.getEventSentiment();
    }
}
