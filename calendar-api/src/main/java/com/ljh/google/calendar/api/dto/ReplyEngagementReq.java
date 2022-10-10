package com.ljh.google.calendar.api.dto;

import com.ljh.google.calendar.core.domain.RequestReplyType;

public class ReplyEngagementReq {
    private RequestReplyType type; // REJECT, ACCEPT

    public ReplyEngagementReq(){}

    public ReplyEngagementReq(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }
}
