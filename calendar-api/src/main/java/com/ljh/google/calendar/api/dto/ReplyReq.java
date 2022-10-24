package com.ljh.google.calendar.api.dto;

import com.ljh.google.calendar.core.domain.RequestReplyType;

public class ReplyReq {
    private RequestReplyType type; // REJECT, ACCEPT

    public ReplyReq(){}

    public ReplyReq(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }
}
