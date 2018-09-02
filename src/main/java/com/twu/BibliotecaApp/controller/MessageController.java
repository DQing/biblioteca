package com.twu.BibliotecaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    private static String MESSAGE = "Welcome To Biblioteca Library";

    @GetMapping(value = "/message")
    ResponseEntity getMessage() {
        Map<String, String> result = new HashMap<>();
        result.put("message", MESSAGE);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
