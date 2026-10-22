package com.piyus.TestingInSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingInSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingInSpringBootApplication.class, args);

		System.out.println("Testing Spring Boot Application");
	}

}
