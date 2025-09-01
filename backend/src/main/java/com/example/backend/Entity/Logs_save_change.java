package com.example.backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Logs_save_change implements Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_save_change;
    private String employees ;
    private String oldMachine;
    private String newMachine;
    private LocalDateTime time;
}
