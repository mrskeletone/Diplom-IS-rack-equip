package com.example.backend.repository;

import com.example.backend.Entity.Logs_taking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLogs_taking extends JpaRepository<Logs_taking,Long> {
}
