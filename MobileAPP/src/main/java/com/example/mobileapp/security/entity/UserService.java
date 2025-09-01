package com.example.mobileapp.security.entity;

import com.example.mobileapp.DTO.LoginRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService  {
    private final RepUser userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(RepUser userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());

        // Хеширование пароля
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
    public boolean exitstUser(User user){
        User user1=userRepository.findByUsername(user.getUsername()).orElseThrow();
        if(user1!=null){
            return true;
        }else {
            return false;
        }
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void save(User user){
        userRepository.save(user);
    }

}
