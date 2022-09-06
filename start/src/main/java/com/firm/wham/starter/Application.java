package com.firm.wham.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ricardo zhou
 */
@SpringBootApplication(scanBasePackages = "com.firm.wham")
@MapperScan("com.firm.wham.infrastructure")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
