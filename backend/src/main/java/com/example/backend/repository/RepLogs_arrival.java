package com.example.backend.repository;

import com.example.backend.Entity.Logs_arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLogs_arrival extends JpaRepository<Logs_arrival,Long> {
}
