package com.timesoft.hr.employeedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class EmployeedataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeedataApplication.class, args);
	}

//	@Bean(initMethod = "migrate")
//	@FlywayDataSource
//	@Primary
//	public Flyway primaryFlyway(FlywayPrimaryProperties properties) {
//		Flyway flyway = new Flyway();
//		flyway.setDataSource(primaryDataSource);
//		flyway.setLocations(properties.getLocation());
//		return flyway;
//	}

}
