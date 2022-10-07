package com.ljh.google.calendar.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginReq {

    private final String email;

    @Size(min = 6, message = "6자리 이상 입력해주세요.")
    @NotBlank
    private final String password;
}
