package com.example.rfid_mbtcp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity

public class Logs_access implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_access;
    private String UID;          // TID (E280116060000207B533B5F3)
    private String flag;

    private LocalDateTime time;
    private boolean access;

    @ManyToOne
    private RFID_readers idRFID_readers;

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Logs_access(Long idLogs_access, String UID, String flag, LocalDateTime time, RFID_readers idRFID_readers, boolean access) {
        this.idLogs_access = idLogs_access;
        this.UID = UID;
        this.flag = flag;
        this.time = time;
        this.idRFID_readers = idRFID_readers;
        this.access=access;
    }

    public Logs_access() {
    }

    public Long getIdLogs_access() {
        return idLogs_access;
    }

    public void setIdLogs_access(Long idLogs_access) {
        this.idLogs_access = idLogs_access;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
}
