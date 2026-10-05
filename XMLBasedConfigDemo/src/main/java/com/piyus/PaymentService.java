package com.piyus;

public class PaymentService {

    private String type;
    private int retryCount;

    public PaymentService(String type, int retryCount) {
        this.type = type;
        this.retryCount = retryCount;
    }

    public void pay(){
        System.out.println("payment done with " + type + " " + retryCount);
    }
}
