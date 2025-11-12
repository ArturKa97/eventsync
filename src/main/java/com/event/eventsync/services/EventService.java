package com.event.eventsync.services;

import com.event.eventsync.entities.Event;
import com.event.eventsync.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public void addEvent (Event event) {
        eventRepository.save(event);
    }

}
