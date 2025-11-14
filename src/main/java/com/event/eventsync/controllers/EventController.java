package com.event.eventsync.controllers;

import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.services.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @PostMapping("/{eventId}/feedback")
    public void addEventFeedback(@PathVariable(value = "eventId") Integer eventId, @RequestBody EventFeedback eventFeedback) throws JsonProcessingException {
        eventService.addEventFeedback(eventId, eventFeedback);
    }

    @PostMapping("/test")
    public EventSentiment test() throws JsonProcessingException {
        return eventService.getEventSentiment();
    }

}
