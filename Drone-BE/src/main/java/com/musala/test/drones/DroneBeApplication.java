package com.musala.test.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneBeApplication.class, args);
	}

}
