package com.event.eventsync.services;

import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void addEventFeedback(Integer eventId, EventFeedback eventFeedback) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")); // very basic exception for set up and testing purposes now
        eventFeedback.setEvent(event);
        event.addFeedback(eventFeedback);
        eventRepository.save(event);
    }
}
