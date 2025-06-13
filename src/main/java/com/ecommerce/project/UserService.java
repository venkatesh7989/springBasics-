package com.ecommerce.project;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserScervice {
    private List<User>userList = new ArrayList<>();
    public List<User> Featchallusers(){
        return userList;
    }
    public List<User>addusers( User user) {
        userList.add(user);
        return userList;
    }
}
