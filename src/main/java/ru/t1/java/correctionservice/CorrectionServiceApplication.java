package ru.t1.java.correctionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CorrectionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorrectionServiceApplication.class, args);
    }

}
