package com.example.application.views.table.service;

import com.example.application.views.table.model.User;
import com.helger.commons.annotation.Translatable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class UserService implements Serializable {

    public static ArrayList<User> users =  new ArrayList<>();

    public ArrayList<User> getUserPerPage(int page, int limit) {
        int arrayLimit = Math.min(limit, users.size() - 1);
        return new ArrayList<>(users.subList((page - 1) * arrayLimit, (page - 1) * arrayLimit + arrayLimit));
    }

    public int getTotal() {
        return users.size();
    }
}
