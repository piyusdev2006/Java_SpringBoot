package com.piyus.SpringBootCoreDemo2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CodeRunner implements CommandLineRunner /*ApplicationRunner*/ {

    private PaymentGateway paymentGateway;

    public CodeRunner(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }



    // ApplicationRunner Method
    // @Override
    // public void run(ApplicationArguments args) throws Exception {
    //     paymentGateway.print();
    // }

    // CommandLineRunner Method
    @Override
    public void run(String... args) throws Exception {
        paymentGateway.print();
    }
}
