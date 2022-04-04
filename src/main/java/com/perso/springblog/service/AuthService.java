package com.perso.springblog.service;

import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.User;
import com.perso.springblog.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record AuthService(UserRepository userRepository) {

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);
    }
}
