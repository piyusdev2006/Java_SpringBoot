package com.piyus.SpringBootCoreDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.piyus")
public class SpringBootCoreDemoApplication {

	public static void main(String[] args) {

		ApplicationContext obj =
				SpringApplication.run(SpringBootCoreDemoApplication.class, args);

		OrderService order = obj.getBean(OrderService.class);
		order.placeOrder();

	}

	@Bean
	public UserService getUserService(){

		return new UserService();
	}

}
