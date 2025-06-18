package com.example.HealthCareSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthCareSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthCareSystemApplication.class, args);
		System.out.println("Welcome to Online HealthCare System");
	}
}
