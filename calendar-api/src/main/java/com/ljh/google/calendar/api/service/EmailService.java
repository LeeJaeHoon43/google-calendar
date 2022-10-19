package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.controller.BatchController;
import com.ljh.google.calendar.api.dto.EngagementEmailStuff;
import com.ljh.google.calendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq req);
}
