package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
