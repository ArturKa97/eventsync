package com.event.eventsync.repositories;

import com.event.eventsync.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT DISTINCT e FROM Event e LEFT JOIN FETCH e.eventFeedbackList ef LEFT JOIN FETCH ef.sentiment")
    List<Event> findAllEventsWithFeedbackAndSentiments();

    @Query("SELECT DISTINCT e FROM Event e LEFT JOIN FETCH e.eventFeedbackList ef LEFT JOIN FETCH ef.sentiment WHERE e.id = :eventId")
    Optional<Event> getEventById(@Param("eventId") Integer eventId);

}
