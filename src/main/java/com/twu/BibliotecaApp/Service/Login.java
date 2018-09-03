package com.twu.BibliotecaApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {
    @Autowired
    private UserList userList;

    public Boolean isLogin() {
        return userList.getCurrentUser() != null;
    }
}
