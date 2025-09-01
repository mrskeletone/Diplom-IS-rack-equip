package com.example.backend.Entity;

import jakarta.persistence.*;
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
public class New_delivery_place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNew_delivery_place;
    private LocalDateTime time;
    private String employees;
    private String location;
    @OneToOne
    private Logs_taking idLogs_taking;
}
