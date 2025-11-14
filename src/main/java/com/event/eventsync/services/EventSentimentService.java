package com.event.eventsync.services;

import com.event.eventsync.entities.EventSentiment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public EventSentiment getEventSentiment() throws JsonProcessingException {
        String requestBody = "{\"inputs\": \"" + "Very good" + "\"}";

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
        List<List<EventSentiment>> nestedList = mapper.readValue(response,
                new TypeReference<List<List<EventSentiment>>>() {});

        List<EventSentiment> predictions = nestedList.get(0);

        EventSentiment topPrediction = predictions.stream()
                .max(Comparator.comparingDouble(EventSentiment::getScore))
                .orElseThrow(() -> new RuntimeException("No sentiment found"));

        return topPrediction;
    }

}
