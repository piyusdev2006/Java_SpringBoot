package com.piyus.notification;

public class PopUpNotificationService implements NotificationService{

    @Override
    public void sendNotification() {
        System.out.println("PopUp Alert sent");
    }
}

