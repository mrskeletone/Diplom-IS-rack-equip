package com.example.mes_opcua;

import lombok.Data;

public class AccessMachine {
    private String nameMachine;
    private boolean access;

    @Override
    public String toString() {
        return "AccessMachine{" +
                "nameMachine='" + nameMachine + '\'' +
                ", access=" + access +
                '}';
    }

    public String getNameMachine() {
        return nameMachine;
    }

    public void setNameMachine(String nameMachine) {
        this.nameMachine = nameMachine;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
