package com.hello;

import com.hello.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

//  Field Injection : injecting dependency through Field
//  but field injection is not recommended
//    @Autowired
    private final PaymentService payment;

//    constructor injection: injecting dependency through constructor ,
//    Recommended Approach
//    @Autowired
    public OrderService(@Qualifier("card") PaymentService payment){
        this.payment = payment;
    }

    public void placeOrder(){

        payment.pay();
        System.out.println("order placed");
    }


//     setter of PaymentService: injecting dependency through setter
//    @Autowired
//    public void setPaymentService(PaymentService payment){
//        this.payment = payment;
//    }
}
