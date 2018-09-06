package com.twu.BibliotecaApp;

import com.twu.BibliotecaApp.Entity.Book;
import com.twu.BibliotecaApp.Entity.Movie;
import com.twu.BibliotecaApp.Service.BookList;
import com.twu.BibliotecaApp.Service.Login;
import com.twu.BibliotecaApp.Service.MovieList;
import com.twu.BibliotecaApp.Service.UserList;
import com.twu.BibliotecaApp.controller.BookController;
import com.twu.BibliotecaApp.controller.MovieController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserList userList;
    @MockBean
    private MovieList movieList;
    @MockBean
    private Login login;


    @Test
    public void should_get_message() throws Exception {
        given(login.isLogin()).willReturn(true);
        List<Movie> arrayList = new ArrayList<>();
        LocalDate localDate = LocalDate.of(2018, 2, 3);
        arrayList.add(new Movie(1, "name1", "author1", 10, localDate, "34E,3f", true));
        arrayList.add(new Movie(2, "name2", "author2", 10, localDate, "34E,3f", true));
        arrayList.add(new Movie(3, "name3", "author3", 10, localDate, "34E,3f", true));
        arrayList.add(new Movie(4, "name4", "author4", 10, localDate, "34E,3f", true));
        arrayList.add(new Movie(5, "name5", "author5", 10, localDate, "34E,3f", false));
        arrayList.add(new Movie(6, "name6", "author6", 10, localDate, "34E,3f", true));
        given(movieList.getMovieList()).willReturn(arrayList);
        mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
