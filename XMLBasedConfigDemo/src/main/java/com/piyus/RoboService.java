package com.piyus;

public class RoboService {

    public RoboService(){
        System.out.println("rono services created");
    }

    public void init(){
        System.out.println("@PostConstruct Phase");
    }

    public void destroy(){
        System.out.println("@PreConstruct Phase");
    }
}
