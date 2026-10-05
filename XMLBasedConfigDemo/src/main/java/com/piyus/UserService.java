package com.piyus;

import java.util.List;

public class UserService {

    private List<String> users;

    public UserService(List<String> usernames){
        users = usernames;
    }

    public List<String> getUsers() {
        return users;
    }
}
