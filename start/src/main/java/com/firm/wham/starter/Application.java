package com.firm.wham.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ricardo zhou
 */
@SpringBootApplication(scanBasePackages = "com.firm.wham")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
