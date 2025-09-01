package com.example.rfid_mbtcp.mes;

import java.util.ArrayList;
import java.util.List;
public class AccessEmployees {
    private String name;
    private String UID;
    private List<AccessMachine> accessMachines=new ArrayList<>();
    public void addList(AccessMachine accessMachine){
        accessMachines.add(accessMachine);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public List<AccessMachine> getAccessMachines() {
        return accessMachines;
    }

    public void setAccessMachines(List<AccessMachine> accessMachines) {
        this.accessMachines = accessMachines;
    }

    @Override
    public String toString() {
        return "AccessEmployees{" +
                "name='" + name + '\'' +
                ", UID='" + UID + '\'' +
                ", accessMachines=" + accessMachines +
                '}';
    }
}
