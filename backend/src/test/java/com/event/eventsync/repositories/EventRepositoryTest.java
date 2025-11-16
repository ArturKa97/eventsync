package com.event.eventsync.repositories;

import com.event.eventsync.entities.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void EventRepository_GetEventById_ShouldReturnEvent() {
        //Given
        Event event = Event.builder()
                .title("test")
                .description("test")
                .build();
        //When
        Event savedEvent = eventRepository.save(event);
        Event fetchedEvent = eventRepository.getEventById(savedEvent.getId()).get();
        //Then
        assertThat(fetchedEvent).isNotNull()
                .isEqualTo(savedEvent);
    }
    @Test
    public void EventRepository_GetEventById_ShouldReturnOptionalEmpty() {
        //Given
        Integer nonExistentId = 999999;
        //When
        Optional<Event> fetchedEvent = eventRepository.getEventById(nonExistentId);
        //Then
        assertThat(fetchedEvent).isEmpty();
    }
    @Test
    public void EventRepository_FindAllEventsWithFeedbackAndSentiments_ReturnsEventList() {
        // Given
        Event event1 = Event.builder().title("test1").description("test1").build();
        Event event2 = Event.builder().title("test2").description("test2").build();
        eventRepository.saveAll(List.of(event1, event2));
        // When
        List<Event> events = eventRepository.findAllEventsWithFeedbackAndSentiments();
        // Then
        assertThat(events).hasSize(2);
    }


}