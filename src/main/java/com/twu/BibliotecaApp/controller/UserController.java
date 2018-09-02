package com.twu.BibliotecaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class UserController {

    @GetMapping(value = "/home")
    ResponseEntity getMessage() {
        Map<String, String> result = new HashMap<>();
        result.put("message", "Welcome To Biblioteca Library");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
