package com.piyus.SpringBootCoreDemo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    // field injection of properties with or without default value
//    @Value("${paymentGateway.type:Stripe}")
//    private String type;
//
//    @Value("${paymentGateway.retryCount:4}")
//    private int retryCount;

    // property injection through constructor with or without default value
//    public PaymentGateway(@Value("${paymentGateway.type:Stripe}")String type,
//                          @Value("${paymentGateway.retryCount:4}")int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }


//    public int getRetryCount() {
//        return retryCount;
//    }
//
//    public void setRetryCount(int retryCount) {
//        this.retryCount = retryCount;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    // property injection through special Paymentproperties configuration class

    private PaymentProperties paymentProperties;

    // constructor
    public PaymentGateway(PaymentProperties paymentProperties){
        this.paymentProperties = paymentProperties;
    }


    // Getter
    public String getType(){
        return paymentProperties.getType();
    }

    public int getRetryCount(){
        return paymentProperties.getRetryCount();
    }

    public int getTimeOut(){
        return paymentProperties.getTimeOut();
    }

    public boolean isEnabled(){

        return paymentProperties.isEnabled();
    }

    public void print(){
        System.out.println(getType());
        System.out.println(getRetryCount());
        System.out.println(isEnabled());
        System.out.println(getTimeOut());
    }
}
