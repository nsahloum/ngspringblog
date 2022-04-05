package com.perso.springblog.service;

import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.Users;
import com.perso.springblog.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(RegisterRequest registerRequest) {
        Users users = new Users();
        users.setUserName(registerRequest.getUsername());
        users.setPassword(registerRequest.getPassword());
        users.setEmail(registerRequest.getEmail());
        userRepository.save(users);
    }
}
