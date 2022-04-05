package com.perso.springblog.controller;

import com.perso.springblog.dto.LogInRequest;
import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.User;
import com.perso.springblog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public User signup(@RequestBody RegisterRequest registerRequest) {
        return authService.signup(registerRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LogInRequest logInRequest) {
        return authService.login(logInRequest);
    }
}
