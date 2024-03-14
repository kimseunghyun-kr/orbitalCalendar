package com.orbital.calendar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "repeat_frequency")
    private String repeatFrequency;

    public Task(String title, String description, LocalDateTime startDate, LocalDateTime dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters omitted for brevity

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE
    }
}

