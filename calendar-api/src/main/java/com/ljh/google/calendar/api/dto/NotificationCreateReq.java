package com.ljh.google.calendar.api.dto;

import com.ljh.google.calendar.core.exception.CalendarException;
import com.ljh.google.calendar.core.exception.ErrorCode;
import com.ljh.google.calendar.core.util.TimeUnit;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class NotificationCreateReq {
    private final String title;
    private final LocalDateTime notifyAt;
    private final RepeatInfo repeatInfo;

    public List<LocalDateTime> getRepeatTimes() {
        if (repeatInfo == null){
            return Collections.singletonList(notifyAt);
        }
        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                    long increment = (long) repeatInfo.inteval.intervalValue * i;
                    switch (repeatInfo.inteval.timeUnit){
                        case DAY:
                            return notifyAt.plusDays(increment);
                        case WEEK:
                            return notifyAt.plusWeeks(increment);
                        case MONTH:
                            return notifyAt.plusMonths(increment);
                        case YEAR:
                            return notifyAt.plusYears(increment);
                        default:
                            throw new CalendarException(ErrorCode.BAD_REQUEST);
                    }
                })
                .collect(Collectors.toList());
    }

    @Data
    public static class RepeatInfo {
        private final Interval inteval;
        private final int times; // 횟수
    }

    @Data
    public static class Interval {
        private final int intervalValue; // 몇일, 몇주, 몇달, 몇년
        private final TimeUnit timeUnit; // DAY, WEEK, MONTH, YEAR
    }
}
