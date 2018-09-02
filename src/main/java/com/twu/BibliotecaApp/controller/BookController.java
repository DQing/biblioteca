package com.twu.BibliotecaApp.controller;

import com.twu.BibliotecaApp.Data.BookList;
import com.twu.BibliotecaApp.Entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @GetMapping(value = "/books")
    ResponseEntity getBooks() {
        List<Book> bookList = new BookList().getBookList();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping(value = "books/{id}")
    ResponseEntity checkoutBook(@PathVariable("id") int id) {
        HashMap<String, Object> result = new HashMap<>();
        List<Book> bookList = new BookList().getBookList();
        new BookList().setBookList(bookList.stream().peek(book -> {
            if (book.getId() == id) {
                book.setCount(book.getCount() - 1);
                result.put("message", "success");
            }
        }).collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/books")
    ResponseEntity returnBook(@RequestBody HashMap<String, String> input) {
        HashMap<String, String> result = new HashMap<>();
        String name = input.get("name");
        String id = input.get("id");
        List<Book> bookList = new BookList().getBookList();
        new BookList().setBookList(bookList.stream().peek(book -> {
            if (name.equals(book.getName()) && book.getId() == Integer.valueOf(id)) {
                book.setCount(book.getCount() + 1);
                result.put("message", "success");
            }
        }).collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
