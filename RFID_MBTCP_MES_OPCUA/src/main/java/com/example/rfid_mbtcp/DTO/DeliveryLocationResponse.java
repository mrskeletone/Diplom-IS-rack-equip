package com.example.rfid_mbtcp.DTO;

import java.io.Serializable;
public class DeliveryLocationResponse implements Serializable {
    private String epc;
    private String location;

    public DeliveryLocationResponse(String epc, String location) {
        this.epc = epc;
        this.location = location;
    }

    public DeliveryLocationResponse() {
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
