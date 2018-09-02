package com.twu.BibliotecaApp.Data;

import com.twu.BibliotecaApp.Entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> MovieList;

    public MovieList() {
        List<Movie> arrayList = new ArrayList<>();
        arrayList.add(new Movie(1, "name1", "author1", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Movie(2, "name2", "author2", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Movie(3, "name3", "author3", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Movie(4, "name4", "author4", 10, "2018-1-1", "34E,3f", true));
        arrayList.add(new Movie(5, "name5", "author5", 10, "2018-1-1", "34E,3f", false));
        arrayList.add(new Movie(6, "name6", "author6", 10, "2018-1-1", "34E,3f", true));
        this.MovieList = arrayList;
    }

    public MovieList(List<Movie> MovieList) {
        this.MovieList = MovieList;
    }

    public List<Movie> getMovieList() {
        return MovieList;
    }

    public void setMovieList(List<Movie> MovieList) {
        this.MovieList = MovieList;
    }
}
