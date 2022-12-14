package com.ljh.google.calendar.batch.job;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendMailBatchReq {
    private Long id; // scheduleId
    private LocalDateTime startAt;
    private String title;
    private String userMail;
}
