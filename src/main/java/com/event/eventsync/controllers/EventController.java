package com.event.eventsync.controllers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.services.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public void addEvent(@RequestBody EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
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
    public EventSentiment test(String feedback) throws JsonProcessingException {
        return eventService.getEventSentiment(feedback);
    }

    @GetMapping("/{eventId}/summary")
    public Map<String, Long> getEventSummary(@PathVariable(value = "eventId") Integer eventId) {
        return eventService.getEventSummary(eventId);
    }

}
