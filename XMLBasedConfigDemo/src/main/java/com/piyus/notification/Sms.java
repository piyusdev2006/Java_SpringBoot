package com.piyus.notification;

public class Sms implements NotificationService{

    @Override
    public void send(){
        System.out.println("Sms Notification");
    }
}
