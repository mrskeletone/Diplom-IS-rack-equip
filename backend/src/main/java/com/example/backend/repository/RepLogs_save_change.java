package com.example.backend.repository;

import com.example.backend.Entity.Logs_save_change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepLogs_save_change extends JpaRepository<Logs_save_change,Long> {
}
