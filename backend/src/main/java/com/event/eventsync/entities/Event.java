package com.event.eventsync.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
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
    @NotNull(message = "Event title cannot be null")
    @NotBlank(message = "Event title cannot be empty")
    @Size(min = 1, max = 100, message = "Event title must be between {min} and {max} characters long")
    private String title;

    @Column(name = "description")
    @NotNull(message = "Event description cannot be null")
    @NotBlank(message = "Event description cannot be empty")
    @Size(min = 1, max = 9999, message = "Event description must be between {min} and {max} characters long")
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
