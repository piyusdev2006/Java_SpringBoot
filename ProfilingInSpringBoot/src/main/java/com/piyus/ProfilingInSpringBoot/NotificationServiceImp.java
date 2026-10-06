package com.piyus.ProfilingInSpringBoot;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NotificationServiceImp implements NotificationService{

    @Override
    public String send(){

        // real notification is sent
        return "Hi, Everyone ! how u are doing?";
    }
}
