package com.example.rfid_mbtcp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity

public class Logs_taking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_taking;
    private String epc;          // EPC-код (3074257BF719D4000000A859)
    private LocalDateTime time;
    private String employees;
    private String location;

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ManyToOne
    private RFID_readers idRFID_readers;



    public Long getIdLogs_taking() {
        return idLogs_taking;
    }

    public void setIdLogs_taking(Long idLogs_taking) {
        this.idLogs_taking = idLogs_taking;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
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



    public Logs_taking() {
    }

    public Logs_taking(Long idLogs_taking, String epc, LocalDateTime time, String employees, String location, RFID_readers idRFID_readers) {
        this.idLogs_taking = idLogs_taking;
        this.epc = epc;
        this.time = time;
        this.employees = employees;
        this.location = location;
        this.idRFID_readers = idRFID_readers;
    }


}
