package com.event.eventsync.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventFeedback> eventFeedbackList;

    public void addFeedback(EventFeedback eventFeedback) {
        eventFeedbackList.add(eventFeedback);
        eventFeedback.setEvent(this);
    }

    public void removeFeedback(EventFeedback eventFeedback) {
        eventFeedbackList.remove(eventFeedback);
        eventFeedback.setEvent(null);
    }

}
