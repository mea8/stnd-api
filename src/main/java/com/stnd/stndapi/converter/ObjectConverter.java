package com.stnd.stndapi.converter;

import com.stnd.stndapi.dto.PostDTO;
import com.stnd.stndapi.dto.UserDTO;
import com.stnd.stndapi.entity.Post;
import com.stnd.stndapi.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {

    public static UserDTO mapUserToUsetDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        if(user.getPosts() != null) {
            List<String> posts = new ArrayList<>();
            for (Post post : user.getPosts()) {
                posts.add(post.getText());
            }
            userDTO.setPosts(posts);
        }
        return userDTO;
    }

    public static PostDTO mapPostToPostDTO(Post post) {
        PostDTO postDTO =  new PostDTO();
        postDTO.setText(post.getText());
        postDTO.setDate(post.getDate());
        postDTO.setUserId(post.getUser().getId());
        return postDTO;
    }

    public static PostDTO mapPostDTO(Post post) {
        PostDTO postDTO =  new PostDTO();
       
        return postDTO;
    }
}
