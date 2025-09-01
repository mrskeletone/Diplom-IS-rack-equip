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
public class Logs_arrival implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_arrival;
    private String epc;          // EPC-код (3074257BF719D4000000A859)
    private String product;     // Пользовательские данные (HEX)
    private int count;     // Пользовательские данные (HEX) 4488 8AC3 . 68AF C30F 0986
    private LocalDateTime time;

    @ManyToOne
    private RFID_readers idRFID_readers;
}
