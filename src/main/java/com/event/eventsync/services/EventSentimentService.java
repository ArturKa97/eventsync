package com.event.eventsync.services;

import com.event.eventsync.entities.EventSentiment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventSentimentService {

    private final WebClient.Builder webClient = WebClient.builder();

    @Value("${huggingface.model.url}")
    private String url;

    @Value("${huggingface.api.token}")
    private String token;

    public EventSentiment getEventSentiment(String feedback) {
        String requestBody = "{\"inputs\": \"" + feedback + "\"}";

        String response = webClient.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)  // Receive raw JSON string
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<List<EventSentiment>> nestedList;
        try {
            nestedList = mapper.readValue(
                    response,
                    new TypeReference<>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Failed to parse JSON into List<List<EventSentiment>>. " + response, e
            );
        }

        if (nestedList.isEmpty() || nestedList.get(0).isEmpty()) {
            throw new EntityNotFoundException("No Event Sentiment predictions returned");
        }

        List<EventSentiment> predictions = nestedList.get(0);

        EventSentiment topPrediction = predictions.stream()
                .max(Comparator.comparingDouble(EventSentiment::getScore))
                .orElseThrow(() -> new EntityNotFoundException("EventSentiment not found"));

        remapLabels(topPrediction);

        return topPrediction;
    }

    public void remapLabels(EventSentiment eventSentiment) {
        switch (eventSentiment.getLabel()) {
            case "LABEL_2" -> eventSentiment.setLabel("Positive");
            case "LABEL_1" -> eventSentiment.setLabel("Neutral");
            case "LABEL_0" -> eventSentiment.setLabel("Negative");
            default -> eventSentiment.setLabel("Unknown");
        }
    }

}
