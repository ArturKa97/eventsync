package com.event.eventsync.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "event_feedback")
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EventFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "feedback")
    @NotNull(message = "Event feedback cannot be null")
    @NotBlank(message = "Event feedback cannot be empty")
    @Size(min = 1, max = 500, message = "Event feedback must be between {min} and {max} characters long")
    private String feedback;

    @Column(name = "time_stamp")
    @CreationTimestamp  // Sets the date to .now() when the entity is first created
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @OneToOne(mappedBy = "eventFeedback", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EventSentiment sentiment;

    public void addEventSentiment(EventSentiment eventSentiment) {
        this.sentiment = eventSentiment;
        eventSentiment.setEventFeedback(this);
    }

}
