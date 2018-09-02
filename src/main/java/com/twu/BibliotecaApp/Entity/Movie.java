package com.twu.BibliotecaApp.Entity;

public class Movie {
    private int id;
    private String name;
    private String director;
    private int count;
    private String publishDate;
    private String location;
    private Boolean state;

    public Movie() {
    }

    public Movie(int id, String name, String director, int count, String publishDate, String location, Boolean state) {
        this.id = id;
        this.name = name;
        this.director = director;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
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
