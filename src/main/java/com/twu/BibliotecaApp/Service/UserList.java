package com.twu.BibliotecaApp.Service;

import com.twu.BibliotecaApp.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserList {
    private List<User> userList;
    private User currentUser;


    public UserList() {
        User user = new User(1, "E1234", "1234", Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
        this.userList = Collections.singletonList(user);
    }

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
