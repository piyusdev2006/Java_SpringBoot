package com.piyus.SpringBootCoreDemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootCoreDemo2Application {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootCoreDemo2Application.class, args);


//		ApplicationContext context
//				=
//				SpringApplication.run(SpringBootCoreDemo2Application.class, args);

//		PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);

//		paymentGateway.setType("Stripe");
//		paymentGateway.setRetryCount(7);

//		System.out.println(paymentGateway.getType());
//		System.out.println(paymentGateway.getRetryCount());
//		System.out.println(paymentGateway.isEnabled());
//		System.out.println(paymentGateway.getTimeOut());


//		paymentGateway.print();
	}

}
