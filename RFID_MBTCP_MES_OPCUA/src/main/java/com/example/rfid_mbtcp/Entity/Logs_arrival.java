package com.example.rfid_mbtcp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
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

    public Long getIdLogs_arrival() {
        return idLogs_arrival;
    }

    public void setIdLogs_arrival(Long idLogs_arrival) {
        this.idLogs_arrival = idLogs_arrival;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public RFID_readers getIdRFID_readers() {
        return idRFID_readers;
    }

    public void setIdRFID_readers(RFID_readers idRFID_readers) {
        this.idRFID_readers = idRFID_readers;
    }

    public Logs_arrival() {
    }

    public Logs_arrival(Long idLogs_arrival, String epc, String product, int count, LocalDateTime time, RFID_readers idRFID_readers) {
        this.idLogs_arrival = idLogs_arrival;
        this.epc = epc;
        this.product = product;
        this.count = count;
        this.time = time;
        this.idRFID_readers = idRFID_readers;
    }
}
