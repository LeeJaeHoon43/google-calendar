package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.dto.AuthUser;
import com.ljh.google.calendar.api.dto.EventCreateReq;
import com.ljh.google.calendar.core.domain.RequestStatus;
import com.ljh.google.calendar.core.domain.entity.Engagement;
import com.ljh.google.calendar.core.domain.entity.Schedule;
import com.ljh.google.calendar.core.domain.entity.User;
import com.ljh.google.calendar.core.domain.entity.repository.EngagementRepository;
import com.ljh.google.calendar.core.domain.entity.repository.ScheduleRepository;
import com.ljh.google.calendar.core.exception.CalendarException;
import com.ljh.google.calendar.core.exception.ErrorCode;
import com.ljh.google.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EmailService emailService;
    private final UserService userService;
    private final EngagementRepository engagementRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(EventCreateReq eventCreateReq, AuthUser authUser) {
        // 이벤트 참여자의 다른 이벤트와 중복이 되면 안됨.
        final List<Engagement> engagementList = engagementRepository.findAll();
        if (engagementList
                .stream()
                .anyMatch(e -> eventCreateReq.getAttendeeIds().contains(e.getAttendee().getId())
                        && e.getRequestStatus() == RequestStatus.ACCEPTED
                        && e.getEvent().isOverlapped(eventCreateReq.getStartAt(), eventCreateReq.getEndAt()))

        ) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }

        final Schedule eventSchedule = Schedule.event(
                eventCreateReq.getTitle(),
                eventCreateReq.getDescription(),
                eventCreateReq.getStartAt(),
                eventCreateReq.getEndAt(),
                userService.findByUserId(authUser.getId()));
        scheduleRepository.save(eventSchedule);
        eventCreateReq.getAttendeeIds()
                .forEach(atId -> {
                    final User attendee = userService.findByUserId(atId);
                    final Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee)
                            .build();
                    engagementRepository.save(engagement);
                    emailService.sendEngagement(engagement);
                });
    }
}
