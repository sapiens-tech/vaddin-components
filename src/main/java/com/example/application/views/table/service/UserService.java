package com.example.application.views.table.service;

import com.example.application.views.table.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class UserService implements Serializable {

    public static ArrayList<User> users =  new ArrayList<>();

    @Autowired
    public UserService() {
        for(int i = 0; i < 247; i++) {
            User user = new User((long) (i + 1), i + "firstname",
                    i + "lastname", "member" + i + "@sapiens.com");
            users.add(user);
        }
    }

    public ArrayList<User> getUserPerPage(int page, int limit) {
        int arrayLimit = Math.min(limit * page, users.size());
        return new ArrayList<>(users.subList((page - 1) * limit, arrayLimit));
    }

    public int getTotal() {
        return users.size();
    }
}
