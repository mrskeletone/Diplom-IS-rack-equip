package com.example.mes_opcua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MesOpcuaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesOpcuaApplication.class, args);
    }

}
