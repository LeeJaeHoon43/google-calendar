package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.dto.AuthUser;
import com.ljh.google.calendar.api.dto.NotificationCreateReq;
import com.ljh.google.calendar.core.domain.entity.Schedule;
import com.ljh.google.calendar.core.domain.entity.User;
import com.ljh.google.calendar.core.domain.entity.repository.ScheduleRepository;
import com.ljh.google.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(NotificationCreateReq notificationCreateReq, AuthUser authUser) {
        final User user = userService.findByUserId(authUser.getId());
        final List<LocalDateTime> notifyAtList = notificationCreateReq.getRepeatTimes();
        notifyAtList.forEach(notifyAt -> {
            final Schedule notificationSchedule =
                    Schedule.notification(
                            notificationCreateReq.getTitle(),
                            notifyAt,
                            user);
            scheduleRepository.save(notificationSchedule);
        });
    }
}
