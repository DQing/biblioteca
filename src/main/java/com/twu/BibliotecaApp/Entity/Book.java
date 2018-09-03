package com.twu.BibliotecaApp.Entity;


import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class Book {
    private int id;
    private String name;
    private String author;
    private int count;
    private LocalDate publishDate;
    private String location;
    private Boolean state;

    public Book() {
    }

    public Book(int id, String name, String author, int count, LocalDate publishDate, String location, Boolean state) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.count = count;
        this.publishDate = publishDate;
        this.location = location;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
