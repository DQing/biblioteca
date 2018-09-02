package com.twu.BibliotecaApp.controller;

import com.twu.BibliotecaApp.Data.MovieList;
import com.twu.BibliotecaApp.Data.MovieList;
import com.twu.BibliotecaApp.Entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    @GetMapping(value = "/movies")
    ResponseEntity getMovies() {
        List<Movie> movieList = new MovieList().getMovieList();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping(value = "movies/{id}")
    ResponseEntity checkoutMovie(@PathVariable("id") int id) {
        HashMap<String, Object> result = new HashMap<>();
        List<Movie> MovieList = new MovieList().getMovieList();
        new MovieList().setMovieList(MovieList.stream().peek(Movie -> {
            if (Movie.getId() == id) {
                Movie.setCount(Movie.getCount() - 1);
                result.put("message", "success");
            }
        }).collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/movies")
    ResponseEntity returnMovie(@RequestBody HashMap<String, String> input) {
        HashMap<String, String> result = new HashMap<>();
        String name = input.get("name");
        String id = input.get("id");
        List<Movie> MovieList = new MovieList().getMovieList();
        new MovieList().setMovieList(MovieList.stream().peek(Movie -> {
            if (name.equals(Movie.getName()) && Movie.getId() == Integer.valueOf(id)) {
                Movie.setCount(Movie.getCount() + 1);
                result.put("message", "success");
            }
        }).collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
