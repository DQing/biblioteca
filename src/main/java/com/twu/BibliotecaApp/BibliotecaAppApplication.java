package com.twu.BibliotecaApp;

import com.twu.BibliotecaApp.Service.UserList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaAppApplication {
    public UserList userList;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaAppApplication.class, args);
    }
}
