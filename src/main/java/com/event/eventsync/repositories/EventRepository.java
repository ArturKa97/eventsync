package com.event.eventsync.repositories;

import com.event.eventsync.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT e FROM Event e LEFT JOIN FETCH e.eventFeedbackList ef LEFT JOIN FETCH ef.sentiment")
    List<Event> findAllEventsWithFeedbackAndSentiments();

}
