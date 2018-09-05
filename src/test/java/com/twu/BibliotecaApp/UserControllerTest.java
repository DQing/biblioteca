package com.twu.BibliotecaApp;

import com.alibaba.fastjson.JSONObject;
import com.twu.BibliotecaApp.Entity.User;
import com.twu.BibliotecaApp.Service.Login;
import com.twu.BibliotecaApp.Service.UserList;
import com.twu.BibliotecaApp.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserList userList;
    @MockBean
    private Login login;


    @Test
    public void should_get_message() throws Exception {
        String MESSAGE = "Welcome To Biblioteca Library";
        mockMvc.perform(get("/message"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE));
    }

    @Test
    public void should_login_success() throws Exception {
        HashMap<String, String> data = new HashMap<>();
        data.put("libraryNumber", "E1234");
        data.put("password", "1234");
        User user = new User(1, "E1234", "1234", Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
        List<User> users = Collections.singletonList(user);
        given(userList.getUserList()).willReturn(users);
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(data)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @Test
    public void should_get_user_info() throws Exception {
        User user = new User(1, "E1234", "1234", Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
        given(login.isLogin()).willReturn(true);
        given(userList.getCurrentUser()).willReturn(user);
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
