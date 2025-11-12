package com.event.eventsync.controllers;

import com.event.eventsync.entities.Event;
import com.event.eventsync.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

}
