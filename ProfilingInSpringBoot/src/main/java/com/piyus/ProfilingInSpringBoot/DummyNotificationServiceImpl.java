package com.piyus.ProfilingInSpringBoot;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default", "staging"})
public class DummyNotificationServiceImpl implements NotificationService {

    @Override
    public String send(){

        // fake or dummy notication, actual notification not sent
        return "Hi, Everyone ! how u are doing?";
    }
}
