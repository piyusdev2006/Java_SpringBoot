package com.piyus.SpringSecurityPractical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityPracticalApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				SpringSecurityPracticalApplication.class,
				args
		);

		System.out.println(
				"Spring Security Practical Application Started"
		);
	}

}
