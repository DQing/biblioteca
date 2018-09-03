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

    @GetMapping(value = "/books/checkout")
    ResponseEntity checkoutBook(@RequestBody HashMap<String, String> bookInfo) {
        if (login.isLogin()) {
            int id = Integer.valueOf(bookInfo.get("id"));
            String name = bookInfo.get("name");
            HashMap<String, Object> result = new HashMap<>();
            List<Book> books = bookList.getBookList();
            bookList.setBookList(books.stream().map(book -> {
                if (book.getId() == id && name.equals(book.getName())) {
                    book.setCount(book.getCount() - 1);
                    addCheckoutBook(id);
                    result.put("message", "success");
                } else {
                    result.put("message", "checkout fail! please checkout again");
                }
                return book;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);

        }
    }

    @GetMapping(value = "/books/{id}")
    ResponseEntity getBookInfo(@PathVariable int id) {
        List<Book> books = bookList.getBookList().stream().filter(book -> book.getId() == id).collect(Collectors.toList());
        return new ResponseEntity<>(books.get(0), HttpStatus.OK);
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
                } else {
                    result.put("message", "return book fail, please return again");
                }
                return book;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
        }
    }
}
