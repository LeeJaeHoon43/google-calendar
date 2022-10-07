package com.ljh.google.calendar.api.util;

import com.ljh.google.calendar.api.dto.EventDto;
import com.ljh.google.calendar.api.dto.NotificationDto;
import com.ljh.google.calendar.api.dto.ScheduleDto;
import com.ljh.google.calendar.api.dto.TaskDto;
import com.ljh.google.calendar.core.domain.entity.Schedule;
import com.ljh.google.calendar.core.exception.CalendarException;
import com.ljh.google.calendar.core.exception.ErrorCode;

public abstract class DtoConverter {

    public static ScheduleDto fromSchedule(Schedule schedule){
        switch (schedule.getScheduleType()){
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .taskAt(schedule.getStartAt())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .title(schedule.getTitle())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
