package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.controller.BatchController;
import com.ljh.google.calendar.api.dto.EngagementEmailStuff;
import com.ljh.google.calendar.core.domain.entity.Engagement;
import com.ljh.google.calendar.core.domain.entity.Share;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq req);
    void sendShareRequestMail(String email, String name, Share.Direction direction);
}
