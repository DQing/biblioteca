package com.twu.BibliotecaApp.controller;

import com.twu.BibliotecaApp.Service.Login;
import com.twu.BibliotecaApp.Service.UserList;
import com.twu.BibliotecaApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class UserController {

    private static String MESSAGE = "Welcome To Biblioteca Library";
    @Autowired
    private UserList userList;
    @Autowired
    private Login login;

    @PostMapping(value = "/login")
    ResponseEntity login(@RequestBody HashMap<String, String> user) {
        String number = user.get("libraryNumber");
        String password = user.get("password");
        List<User> currentUser = userList.getUserList().stream()
                .filter(item -> item.getLibraryNumber().equals(number) && item.getPassword().equals(password))
                .collect(Collectors.toList());
        if (currentUser.size() != 0) {
            userList.setCurrentUser(currentUser.get(0));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no such user", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users")
    ResponseEntity getUserInfo(@PathVariable int id) {
        if (login.isLogin()) {
            User currentUser = userList.getCurrentUser();
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/message")
    ResponseEntity getMessage() {
        Map<String, String> result = new HashMap<>();
        result.put("message", MESSAGE);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}