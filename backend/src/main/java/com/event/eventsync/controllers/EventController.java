package com.event.eventsync.controllers;

import com.event.eventsync.dtos.EventDTO;
import com.event.eventsync.dtos.EventFeedbackDTO;
import com.event.eventsync.dtos.SwaggerEventDTO;
import com.event.eventsync.dtos.SwaggerEventFeedbackDTO;
import com.event.eventsync.entities.EventSentiment;
import com.event.eventsync.services.EventService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(description = "Post endpoint for adding an Event",
            summary = "Create and add an Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event was successfully created and added", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping
    public void addEvent(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Event object to be created and added", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SwaggerEventDTO.class))) @RequestBody EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
    }

    @Operation(description = "Get endpoint for getting all Events",
            summary = "Access all Events with their full info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all Events with their full info",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class))),
            @ApiResponse(responseCode = "404", description = "No Events found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping
    public List<EventDTO> getEvents() {
        return eventService.getEvents();
    }

    @Operation(description = "Get endpoint for getting a single Event",
            summary = "Access a single Event with it's full info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a single Events with its full info",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDTO.class))),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event was not found with the provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping("/{eventId}")
    public EventDTO getEventById(@Parameter(in = ParameterIn.PATH, description = "Id of the Event", required = true) @PathVariable(value = "eventId") Integer eventId) {
        return eventService.getEventById(eventId);
    }

    @Operation(description = "Post endpoint for adding feedback to the Event",
            summary = "Create and add feedback to the Event selected by provided Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event feedback was successfully created and added to the selected Event", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event was not found with the provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/{eventId}/feedback")
    public void addEventFeedback(@Parameter(in = ParameterIn.PATH, description = "Id of the Event", required = true) @PathVariable(value = "eventId") Integer eventId,
                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "EventFeedback object to be created and added to Event", required = true,
                                         content = @Content(mediaType = "application/json",
                                                 schema = @Schema(implementation = SwaggerEventFeedbackDTO.class))) @RequestBody EventFeedbackDTO eventFeedbackDTO) {
        eventService.addEventFeedback(eventId, eventFeedbackDTO);
    }

    @Hidden
    @PostMapping("/test")
    public EventSentiment test(String feedback) {
        return eventService.getEventSentiment(feedback);
    }

    @Operation(
            description = "Get endpoint for retrieving an Event's summary",
            summary = "Access the summary of an Event by its Id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the summary of the Event",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Map.class,
                                    description = "Map of summary values for the Event"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event was not found with the provided Id",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Bad request, default response code for when something unexpected happens",
                    content = @Content
            )
    })
    @GetMapping("/{eventId}/summary")
    public Map<String, Long> getEventSummary(@Parameter(in = ParameterIn.PATH, description = "Id of the Event", required = true) @PathVariable(value = "eventId") Integer eventId) {
        return eventService.getEventSummary(eventId);
    }
    @Operation(description = "Delete endpoint for deleting a single Event",
            summary = "Remove a single Event with the provided Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletes the Event successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Event was not found with the provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @DeleteMapping("/{eventId}")
    public void deleteEventById(@Parameter(in = ParameterIn.PATH, description = "Id of the Event", required = true) @PathVariable(value = "eventId") Integer eventId) {
        eventService.deleteEventById(eventId);
    }

}
