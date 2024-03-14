package com.orbital.calendar.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class TaskBuilder {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Task.TaskStatus status;
    private String repeatFrequency;

    public Task build() {
        return new Task(title, description, startDate, dueDate, status);
    }

    // Setters for optional fields

    public TaskBuilder id(Long id) {
        this.id = id;
        return this;
    }
}
