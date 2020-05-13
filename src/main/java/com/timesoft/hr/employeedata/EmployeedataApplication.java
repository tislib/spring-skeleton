package com.timesoft.hr.employeedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeedataApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeedataApplication.class, args);
    }

}
