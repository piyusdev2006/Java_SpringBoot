package com.piyus.service;

import com.piyus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private Map<Integer, User> userDb;

    public UserService() {
        userDb = new HashMap<>();
    }


    public User createUser(User userReq){
        userDb.put(userReq.getId(), userReq);
        return userReq;
    }

    public List<User> getAllUsers(){

        List<User> userResponse = new ArrayList<>();
        for(User user : userDb.values()){
            userResponse.add(user);
        }

        return userResponse;
    }

    public User getUserById(Integer id){

        return userDb.getOrDefault(id, null);
    }

}