package com.twu.BibliotecaApp.Entity;

import org.springframework.stereotype.Service;

import java.util.List;

public class User {
    private int id;
    private String libraryNumber;
    private String password;
    private List<Integer> bookList;
    private List<Integer> movieList;

    public User() {
    }

    public User(int id, String libraryNumber, String password, List<Integer> bookList, List<Integer> movieList) {
        this.id = id;
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.bookList = bookList;
        this.movieList = movieList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getBookList() {
        return bookList;
    }

    public void setBookList(List<Integer> bookList) {
        this.bookList = bookList;
    }

    public List<Integer> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Integer> movieList) {
        this.movieList = movieList;
    }
}
