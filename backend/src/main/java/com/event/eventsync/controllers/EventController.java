package com.event.eventsync.controllers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.services.EventService;
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
    public List<EventDTO> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{eventId}")
    public EventDTO getEventById(@PathVariable(value = "eventId") Integer eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping("/{eventId}/feedback")
    public void addEventFeedback(@PathVariable(value = "eventId") Integer eventId, @RequestBody EventFeedbackDTO eventFeedbackDTO) {
        eventService.addEventFeedback(eventId, eventFeedbackDTO);
    }

    @PostMapping("/test")
    public EventSentiment test(String feedback) {
        return eventService.getEventSentiment(feedback);
    }

    @GetMapping("/{eventId}/summary")
    public Map<String, Long> getEventSummary(@PathVariable(value = "eventId") Integer eventId) {
        return eventService.getEventSummary(eventId);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEventById(@PathVariable(value = "eventId") Integer eventId) {
        eventService.deleteEventById(eventId);
    }

}
