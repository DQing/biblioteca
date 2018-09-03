package com.twu.BibliotecaApp.controller;

import com.twu.BibliotecaApp.Service.BookList;
import com.twu.BibliotecaApp.Service.Login;
import com.twu.BibliotecaApp.Service.UserList;
import com.twu.BibliotecaApp.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private UserList userList;

    @Autowired
    private BookList bookList;

    @Autowired
    private Login login;

    @GetMapping(value = "/books")
    ResponseEntity getBooks() {
        if (login.isLogin()) {
            return new ResponseEntity<>(bookList.getBookList(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "books/{id}")
    ResponseEntity checkoutBook(@PathVariable("id") int id) {
        if (login.isLogin()) {
            HashMap<String, Object> result = new HashMap<>();
            List<Book> books = bookList.getBookList();
            bookList.setBookList(books.stream().map(book -> {
                if (book.getId() == id) {
                    book.setCount(book.getCount() - 1);
                    addCheckoutBook(id);
                    result.put("message", "success");
                }
                return book;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);

        }
    }

    private void addCheckoutBook(int id) {
        List<Integer> integers = userList.getCurrentUser().getBookList();
        List<Integer> list = new ArrayList<>();
        for (int item : integers) {
            list.add(item);
        }
        list.add(id);
        userList.getCurrentUser().setBookList(list);
    }

    private void updateUserBooks(int id) {
        List<Integer> bookIdList = userList.getCurrentUser().getBookList();
        List<Integer> integers = bookIdList.stream().filter(bookId -> bookId != id).collect(Collectors.toList());
        userList.getCurrentUser().setBookList(integers);
    }

    @PostMapping(value = "/books")
    ResponseEntity returnBook(@RequestBody HashMap<String, String> input) {
        if (login.isLogin()) {
            HashMap<String, String> result = new HashMap<>();
            String name = input.get("name");
            String id = input.get("id");
            bookList.setBookList(bookList.getBookList().stream().map(book -> {
                if (name.equals(book.getName()) && book.getId() == Integer.valueOf(id)) {
                    book.setCount(book.getCount() + 1);
                    updateUserBooks(book.getId());
                    result.put("message", "success");
                }
                return book;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
        }
    }
}
