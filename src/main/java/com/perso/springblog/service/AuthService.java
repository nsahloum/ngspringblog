package com.perso.springblog.service;

import com.perso.springblog.dto.RegisterRequest;
import com.perso.springblog.model.Users;
import com.perso.springblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users signup(RegisterRequest registerRequest) {
        Users users = new Users();
        users.setUserName(registerRequest.getUsername());
        users.setPassword(encodePassword(registerRequest.getPassword()));
        users.setEmail(registerRequest.getEmail());
        userRepository.save(users);
        return users;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
