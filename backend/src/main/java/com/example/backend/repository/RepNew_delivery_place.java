package com.example.backend.repository;

import com.example.backend.Entity.New_delivery_place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepNew_delivery_place extends JpaRepository<New_delivery_place,Long> {
}
