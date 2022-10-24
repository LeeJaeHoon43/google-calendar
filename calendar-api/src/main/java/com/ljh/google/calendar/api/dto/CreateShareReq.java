package com.ljh.google.calendar.api.dto;

import com.ljh.google.calendar.core.domain.entity.Share;
import lombok.Data;

@Data
public class CreateShareReq {
    private final Long toUserId;
    private final Share.Direction direction;
}
