package org.example;

import java.io.Serializable;

public class RfidScanEvent implements Serializable {
    private String epc;
    private String readerId;
    private String  location;
    private String username;
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RfidScanEvent(String epc, String readerId, String location, String username) {
        this.epc = epc;
        this.readerId = readerId;
        this.location = location;
        this.username = username;
    }

    public RfidScanEvent() {
    }
}
