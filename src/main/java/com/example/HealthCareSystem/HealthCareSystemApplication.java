package com.example.HealthCareSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@EnableScheduling
public class HealthCareSystemApplication implements WebMvcConfigurer {
	// @Value("${cors.allowed-origins}")
	// private String allowedOrigins;

	public static void main(String[] args) {
		SpringApplication.run(HealthCareSystemApplication.class, args);
		System.out.println("Welcome to Online HealthCare System");
	}

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// 	registry.addMapping("/**")
	// 		.allowedOrigins(allowedOrigins.split(","))
	// 		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
	// 		.allowedHeaders("Authorization", "Content-Type", "Accept", "X-Requested-With")
	// 		.exposedHeaders("Authorization", "Content-Type", "Accept")
	// 		.allowCredentials(true)
	// 		.maxAge(3600);
	// }
}
