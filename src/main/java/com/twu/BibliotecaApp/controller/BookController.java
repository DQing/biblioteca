package com.twu.BibliotecaApp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BookController {

    @GetMapping(value = "/books")
    ResponseEntity getBooks() {
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        File file = new File("src/main/java/com/twu/BibliotecaApp/Data/Book.json");
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[2048];
            inputStream.read(data);
            JSONArray jsonArray = new JSONArray(new String(data));
            jsonArray.forEach((item) -> {
                HashMap<String, Object> itemMap = new HashMap<>();
                JSONObject jsonObject = new JSONObject(item.toString());
                itemMap.put("id", jsonObject.getInt("id"));
                itemMap.put("url", jsonObject.getString("url"));
                itemMap.put("name", jsonObject.getString("name"));
                itemMap.put("state", jsonObject.getBoolean("state"));
                itemMap.put("count", jsonObject.getInt("count"));
                itemMap.put("publishDate", jsonObject.getString("publishDate"));
                itemMap.put("author", jsonObject.getString("author"));
                itemMap.put("location", jsonObject.getString("location"));
                result.add(itemMap);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
