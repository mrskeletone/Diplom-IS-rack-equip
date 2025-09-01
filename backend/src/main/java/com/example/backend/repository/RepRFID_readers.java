package com.example.backend.repository;

import com.example.backend.Entity.RFID_readers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepRFID_readers extends JpaRepository<RFID_readers,Long> {
}
