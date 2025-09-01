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
public class Logs_taking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_taking;
    private String epc;          // EPC-код (3074257BF719D4000000A859)
    private String tid;          // TID (E280116060000207B533B5F3)
    private String product;     // Пользовательские данные (HEX)
    private String count;     // Пользовательские данные (HEX)
    private LocalDateTime time;

    @ManyToOne
    private RFID_readers idRFID_readers;

    @OneToOne(mappedBy = "idLogs_taking")
    private New_delivery_place newDeliveryPlace;
}
