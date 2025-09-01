package com.example.rfid_mbtcp.Entity;

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
import jakarta.persistence.*;

@Entity

public class Logs_save_change implements Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLogs_save_change;
    private String UID;
    private String machine;
    private boolean access;
    private LocalDateTime time;

    public Long getIdLogs_save_change() {
        return idLogs_save_change;
    }

    public void setIdLogs_save_change(Long idLogs_save_change) {
        this.idLogs_save_change = idLogs_save_change;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Logs_save_change() {
    }

    public Logs_save_change(Long idLogs_save_change, String UID, String machine, boolean access, LocalDateTime time) {
        this.idLogs_save_change = idLogs_save_change;
        this.UID = UID;
        this.machine = machine;
        this.access = access;
        this.time = time;
    }
}
