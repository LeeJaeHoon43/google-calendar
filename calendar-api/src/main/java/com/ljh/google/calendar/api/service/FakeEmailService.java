package com.ljh.google.calendar.api.service;

import com.ljh.google.calendar.api.controller.BatchController;
import com.ljh.google.calendar.api.dto.EngagementEmailStuff;
import com.ljh.google.calendar.core.domain.entity.Share;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService{

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println("send email. email : " + stuff.getSubject());
    }

    @Override
    public void sendAlarmMail(BatchController.SendMailBatchReq req) {
        System.out.println("send alarm. " + req.toString());
    }

    @Override
    public void sendShareRequestMail(String email, String name, Share.Direction direction) {
        System.out.println("send share request mail. " + email + ", " + name + ", " + direction);
    }
}
