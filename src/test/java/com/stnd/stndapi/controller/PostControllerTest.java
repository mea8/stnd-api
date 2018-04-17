package com.stnd.stndapi.controller;

import com.stnd.stndapi.dto.PostDTO;
import com.stnd.stndapi.service.PostService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    public void getPostsByUserId() throws Exception {
        //Given
        List<PostDTO> postDTOS = new ArrayList<>();
        given(postService.findPostsByUserId(anyLong())).willReturn(postDTOS);

        // When
        ResultActions result = this.mvc.perform(get("/posts/users/{id}", 1001L)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        // Then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void retrieveTimeline() throws Exception {

        //Given
        List<PostDTO> postDTOS = new ArrayList<>();
        PostDTO postDTO = new PostDTO();
        postDTO.setText("test post");
        postDTOS.add(postDTO);
        given(postService.findAllPosts(anyLong())).willReturn(postDTOS);

        // When
        ResultActions result = this.mvc.perform(get("/timeline/users/{userId}", 1001L)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        // Then
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$", hasSize(1)));
        result.andExpect(jsonPath("$[0].text", is(postDTOS.get(0).getText())));
    }

}
