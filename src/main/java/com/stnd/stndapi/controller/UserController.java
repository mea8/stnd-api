package com.stnd.stndapi.controller;

import com.stnd.stndapi.dto.UserDTO;
import com.stnd.stndapi.entity.User;
import com.stnd.stndapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        logger.info("Getting all users... ");
        List<User> users = userService.findAll();
        logger.info("Getting all users...done.");
        return users;
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable long id) {
        logger.info("Getting user for id {}", id);
        UserDTO users = userService.findUserById(id);

        return users;
    }

    @RequestMapping(value= "/follow", method = RequestMethod.POST)
    ResponseEntity<Void> followUser(@RequestParam(value="userId", required=true) long userId,
                           @RequestParam(value="followed", required=true) long followed) {

        logger.info("Follow user.. ");
        userService.followUser(userId,followed);
        return ResponseEntity.noContent().build();
    }
}
