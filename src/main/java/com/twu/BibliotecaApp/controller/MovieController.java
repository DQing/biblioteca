package com.twu.BibliotecaApp.controller;

import com.twu.BibliotecaApp.Service.Login;
import com.twu.BibliotecaApp.Service.MovieList;
import com.twu.BibliotecaApp.Entity.Movie;
import com.twu.BibliotecaApp.Service.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {
    @Autowired
    private MovieList movieList;

    @Autowired
    private Login login;
    @Autowired
    private UserList userList;

    @GetMapping(value = "/movies")
    ResponseEntity getMovies() {
        if (login.isLogin()) {
            return new ResponseEntity<>(movieList.getMovieList(), HttpStatus.OK);
        }
        return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "movies/{id}")
    ResponseEntity checkoutMovie(@PathVariable("id") int id) {
        HashMap<String, Object> result = new HashMap<>();
        if (login.isLogin()) {

            movieList.setMovieList(movieList.getMovieList().stream().map(movie -> {
                if (movie.getId() == id) {
                    movie.setCount(movie.getCount() - 1);
                    addCheckoutMovie(id);
                    result.put("message", "success");
                }
                return movie;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
        }
    }

    private void addCheckoutMovie(int id) {
        List<Integer> integers = userList.getCurrentUser().getMovieList();
        List<Integer> list = new ArrayList<>();
        for (int item : integers) {
            list.add(item);
        }
        list.add(id);
        userList.getCurrentUser().setMovieList(list);
    }

    private void updateUserMovies(int id) {
        List<Integer> movieList = userList.getCurrentUser().getMovieList();
        List<Integer> integers = movieList.stream().filter(movieId -> movieId != id).collect(Collectors.toList());
        userList.getCurrentUser().setMovieList(integers);
    }

    @PostMapping(value = "/movies")
    ResponseEntity returnMovie(@RequestBody HashMap<String, String> input) {
        HashMap<String, String> result = new HashMap<>();
        if (login.isLogin()) {
            String name = input.get("name");
            String id = input.get("id");
            List<Movie> MovieList = new MovieList().getMovieList();
            new MovieList().setMovieList(MovieList.stream().map(movie -> {
                if (name.equals(movie.getName()) && movie.getId() == Integer.valueOf(id)) {
                    movie.setCount(movie.getCount() + 1);
                    updateUserMovies(Integer.valueOf(id));
                    result.put("message", "success");
                }
                return movie;
            }).collect(Collectors.toList()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("please login !", HttpStatus.FORBIDDEN);
        }
    }
}
