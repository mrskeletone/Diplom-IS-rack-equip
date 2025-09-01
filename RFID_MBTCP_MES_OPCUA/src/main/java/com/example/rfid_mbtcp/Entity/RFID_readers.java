package com.example.rfid_mbtcp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity

public class RFID_readers implements Serializable {
    //TODO id строится как ** , где 1 цифра тип оборудования 1 - прием , 2 - доступ , 3 - носильшик , а 2 цифра (или больше)
    @Id
    @Cascade(CascadeType.ALL)
    private Long idRFID_readers;
    private String location;
    private String type;
    private String machine;
    @OneToMany(mappedBy = "idRFID_readers")
    private List<Logs_access> logsAccess;
    @OneToMany(mappedBy = "idRFID_readers")
    private List<Logs_arrival> logsArrivals;
    @OneToMany(mappedBy = "idRFID_readers")
    private List<Logs_taking> logsTakings;

    public Long getIdRFID_readers() {
        return idRFID_readers;
    }

    public void setIdRFID_readers(Long idRFID_readers) {
        this.idRFID_readers = idRFID_readers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public List<Logs_access> getLogsAccess() {
        return logsAccess;
    }

    public void setLogsAccess(List<Logs_access> logsAccess) {
        this.logsAccess = logsAccess;
    }

    public List<Logs_arrival> getLogsArrivals() {
        return logsArrivals;
    }

    public void setLogsArrivals(List<Logs_arrival> logsArrivals) {
        this.logsArrivals = logsArrivals;
    }

    public List<Logs_taking> getLogsTakings() {
        return logsTakings;
    }

    public void setLogsTakings(List<Logs_taking> logsTakings) {
        this.logsTakings = logsTakings;
    }

    public RFID_readers() {
    }

    public RFID_readers(Long idRFID_readers, String location, String type, String machine, List<Logs_access> logsAccess, List<Logs_arrival> logsArrivals, List<Logs_taking> logsTakings) {
        this.idRFID_readers = idRFID_readers;
        this.location = location;
        this.type = type;
        this.machine = machine;
        this.logsAccess = logsAccess;
        this.logsArrivals = logsArrivals;
        this.logsTakings = logsTakings;
    }
}
