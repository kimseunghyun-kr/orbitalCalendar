package com.orbital.calendar.controller;

import com.orbital.calendar.domain.Task;
import com.orbital.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/getTask")
    public Page<Task> getTaskAtPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String taskTitle) {
        Pageable pageable = PageRequest.of(page, size);
        if (taskTitle != null && !taskTitle.isEmpty()) {
            return calendarService.getTasksByTitle(taskTitle, pageable);
        } else {
            return calendarService.findAllTasks(pageable);
        }
    }

    @PostMapping("/updateTask")
    public void updateTask(@RequestParam Long id,
                            @RequestBody Task task) {
        calendarService.updateTask(id, task);
    }

    @PostMapping("/deleteTask")
    public void updateTask(@RequestParam Long id) {
        calendarService.deleteTask(id);
    }

    @PostMapping("/createTask")
    public Long createTask(@RequestBody Task task) {


        return calendarService.createTask(task);
    }


}
