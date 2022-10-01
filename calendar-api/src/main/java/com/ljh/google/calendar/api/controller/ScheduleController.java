package com.ljh.google.calendar.api.controller;

import com.ljh.google.calendar.api.dto.AuthUser;
import com.ljh.google.calendar.api.dto.TaskCreateReq;
import com.ljh.google.calendar.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq, AuthUser authUser){
        taskService.create(taskCreateReq, authUser);
        return ResponseEntity.ok().build();
    }
}