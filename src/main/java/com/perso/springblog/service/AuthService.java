package com.perso.springblog.service;

import com.perso.springblog.dto.LogInRequest;
import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.User;
import com.perso.springblog.repository.UserRepository;
import com.perso.springblog.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public User signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);
        return user;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String login(LogInRequest logInRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }
}
