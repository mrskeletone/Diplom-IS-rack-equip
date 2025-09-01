package com.example.backend.repository;

import com.example.backend.Entity.Logs_access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLogs_access extends JpaRepository<Logs_access ,Long> {
}
