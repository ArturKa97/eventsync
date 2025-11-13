package com.event.eventsync.services;

import com.event.eventsync.entities.Event;
import com.event.eventsync.entities.EventFeedback;
import com.event.eventsync.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private WebClient.Builder webClient = WebClient.builder();

    @Value("${huggingface.model.url}")
    private String url;

    @Value("${huggingface.api.token}")
    private String token;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
    public List<Event> getEvents() {
        return eventRepository.findAllEventsWithFeedbackAndSentiments();
    }

    public void addEventFeedback(Integer eventId, EventFeedback eventFeedback) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")); // very basic exception for set up and testing purposes now
        eventFeedback.setEvent(event);
        event.addFeedback(eventFeedback);
        eventRepository.save(event);
    }

    public String getEventSentiment() {
        String requestBody = "{\"inputs\": \"" + "Very good" + "\"}";

        return webClient.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)  // Receive raw JSON string
                .block();
    }

}
