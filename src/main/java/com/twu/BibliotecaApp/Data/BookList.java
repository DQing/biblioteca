package com.twu.BibliotecaApp.Data;

import com.twu.BibliotecaApp.Entity.Book;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookList {
    private List<Book> bookList;

    public BookList() {
        List<Book> arrayList = new ArrayList<>();
        arrayList.add(new Book(1, "name1", "author1", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Book(2, "name2", "author2", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Book(3, "name3", "author3", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Book(4, "name4", "author4", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Book(5, "name5", "author5", 10, "2018-1-1", "34E,3f", false));
        arrayList.add(new Book(6, "name6", "author6", 10, "2018-1-1", "34E,3f", true));
        bookList = arrayList;
    }

    public BookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
