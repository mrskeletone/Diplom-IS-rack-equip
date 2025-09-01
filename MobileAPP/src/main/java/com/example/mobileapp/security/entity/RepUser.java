package com.example.mobileapp.security.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepUser extends JpaRepository<User,Long> {

     Optional<User> findByUsername(String username);
}
