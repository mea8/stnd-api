package com.stnd.stndapi.controller;

import com.stnd.stndapi.dto.PostDTO;
import com.stnd.stndapi.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/timeline/users/{userId}", method= RequestMethod.GET)
    public List<PostDTO> getPostsForUser(@PathVariable long userId) {
        logger.info("Getting posts for user... {}", userId);
        List<PostDTO> posts = postService.findAllPosts(userId);
        return posts;
    }

    @RequestMapping(value = "/posts/users/{id}", method= RequestMethod.GET)
    public List<PostDTO> getPostsByUserId(@PathVariable long id) {
        logger.info("Getting posts for user id {}", id);
        List<PostDTO> posts = postService.findPostsByUserId(id);
        return posts;
    }

    @RequestMapping(value= "/posts", method = RequestMethod.POST)
    ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDto) {

        logger.info("Adding post.. ");
        PostDTO result = postService.create(postDto);
        return ResponseEntity.ok(result);
    }
}
