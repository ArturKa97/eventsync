package com.event.eventsync.controllers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.services.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
class EventControllerTest {

    @MockitoBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void EventController_addEvent_ShouldReturnIsOk() throws Exception {
        //Given
        EventDTO eventDTO = EventDTO.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();
        //When
        ResultActions response = mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDTO)));
        //Then
        response
                .andExpect(status().isOk());
        verify(eventService).addEvent(eventDTO);
    }
    @Test
    public void EventController_GetEvents_ShouldReturnEventDTOList() throws Exception {
        //Given
        EventDTO eventDTO1 = EventDTO.builder()
                .id(1)
                .title("test1")
                .description("test1")
                .build();
        EventDTO eventDTO2 = EventDTO.builder()
                .id(2)
                .title("test2")
                .description("test2")
                .build();
        List<EventDTO> eventDTOList = List.of(eventDTO1, eventDTO2);

        when(eventService.getEvents()).thenReturn(eventDTOList);
        //When
        ResultActions response = mockMvc.perform(get("/events")
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(eventDTOList)));

        verify(eventService).getEvents();
    }

    @Test
    public void EventController_DeleteEventById_ShouldReturnIsOk() throws Exception {
        //Given
        Integer eventId = 1;
        //When
        ResultActions response = mockMvc.perform(delete("/events/{eventId}", eventId));
        //Then
        response
                .andExpect(status().isOk());
        verify(eventService).deleteEventById(eventId);
    }
    @Test
    public void EventController_GetEventById_ShouldReturnEventDTO() throws Exception {
        //Given
        Integer eventId = 1;
        EventDTO eventDTO = EventDTO.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();
        when(eventService.getEventById(eventId)).thenReturn(eventDTO);
        //When
        ResultActions response = mockMvc.perform(get("/events/{eventId}", eventId)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(eventDTO)));

        verify(eventService).getEventById(eventId);
    }
    @Test
    public void EventController_AddEventFeedback_ShouldReturnIsOk() throws Exception {
        //Given
        Integer eventId = 1;
        EventFeedbackDTO eventFeedbackDTO = EventFeedbackDTO.builder()
                .id(1)
                .feedback("test")
                .build();
        //When
        ResultActions response = mockMvc.perform(post("/events/{eventId}/feedback", eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventFeedbackDTO)));
        //Then
        response
                .andExpect(status().isOk());
        verify(eventService).addEventFeedback(eventId, eventFeedbackDTO);

    }
    @Test
    public void EventController_GetEventSummary_ShouldReturnSummaryMap() throws Exception {
        // Given
        Integer eventId = 1;
        Map<String, Long> summaryMap = Map.of(
                "Positive", 50L,
                "Neutral", 10L,
                "Negative", 1L
        );
        when(eventService.getEventSummary(eventId)).thenReturn(summaryMap);

        // When
        ResultActions response = mockMvc.perform(get("/events/{eventId}/summary", eventId)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(summaryMap)));

        verify(eventService).getEventSummary(eventId);
    }

}

