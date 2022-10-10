package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.dto.AuthUser;
import com.ljh.google.calendar.api.dto.TaskCreateReq;
import com.ljh.google.calendar.core.domain.entity.Schedule;
import com.ljh.google.calendar.core.domain.entity.repository.ScheduleRepository;
import com.ljh.google.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(TaskCreateReq taskCreateReq, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(
                taskCreateReq.getTitle(),
                taskCreateReq.getDescription(),
                taskCreateReq.getTaskAt(),
                userService.findByUserId(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }
}