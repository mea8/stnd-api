package com.stnd.stndapi.controller;

import com.stnd.stndapi.dto.UserDTO;
import com.stnd.stndapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUser() throws Exception {
        //Given
        UserDTO user = new UserDTO();
        user.setUsername("test user");
        given(userService.findUserById(anyLong())).willReturn(user);

        // When
        ResultActions result = this.mvc.perform(get("/users/1001")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        // Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.username", is(user.getUsername())));

    }

    @Test
    public void followUser() throws Exception {

        // When
        ResultActions result = this.mvc.perform(post("/users/follow/")
                .param("userId", "1001")
                .param("followed", "1003")
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        // Then
        result.andExpect(status().isNoContent());
    }


}
