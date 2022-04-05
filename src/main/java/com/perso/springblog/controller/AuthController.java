package com.perso.springblog.controller;

import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.Users;
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
    public Users signup(@RequestBody RegisterRequest registerRequest) {
        return authService.signup(registerRequest);
    }
}
