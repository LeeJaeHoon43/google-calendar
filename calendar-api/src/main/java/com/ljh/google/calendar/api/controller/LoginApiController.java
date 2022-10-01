package com.ljh.google.calendar.api.controller;

import com.ljh.google.calendar.api.dto.LoginReq;
import com.ljh.google.calendar.api.dto.SignUpReq;
import com.ljh.google.calendar.api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> signup(@RequestBody SignUpReq signUpReq, HttpSession session){
        loginService.signUp(signUpReq, session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(@RequestBody LoginReq loginReq, HttpSession session){
        loginService.login(loginReq, session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        loginService.logout(session);
        return ResponseEntity.ok().build();
    }
}
