package com.orbital.calendar.service;

import com.orbital.calendar.domain.Task;
import com.orbital.calendar.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    private final TaskRepository taskRepository;

    @Autowired
    public CalendarService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Long createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    public Page<Task> findAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> getTasksByTitle(String taskTitle, Pageable pageable) {
        return taskRepository.findTaskByTitle(taskTitle, pageable);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public void updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStartDate(updatedTask.getStartDate());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        task.setRepeatFrequency(updatedTask.getRepeatFrequency());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
