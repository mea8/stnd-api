package com.stnd.stndapi.service;

import com.stnd.stndapi.converter.ObjectConverter;
import com.stnd.stndapi.dao.PostDAO;
import com.stnd.stndapi.dao.UserDAO;
import com.stnd.stndapi.dto.PostDTO;
import com.stnd.stndapi.entity.Post;
import com.stnd.stndapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PostService {

    private PostDAO postDAO;
    private UserDAO userDAO;

    @Autowired
    public PostService(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PostDTO create(PostDTO postDTO) {

        User user=null;
        if (postDTO.getUserId() != 0) {
            user = userDAO.findById(postDTO.getUserId());
        }
        if(user == null) {
            user = userDAO.create(new User("test username"));
        }
        Post result = postDAO.create(new Post(user,postDTO.getText(), LocalDateTime.now()));
        return ObjectConverter.mapPostToPostDTO(result);
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findAllPosts(long userId){
        List<Post> posts= postDAO.findForUserId(userId);
        List<PostDTO> postDTOS = posts.stream()
                .map(ObjectConverter::mapPostToPostDTO)
                .collect(Collectors.toList());
        return postDTOS;
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findPostsByUserId(long id){
        List<Post> posts= postDAO.findByUserId(id);
        List<PostDTO> postDTOS = posts.stream()
                .map(ObjectConverter::mapPostToPostDTO)
                .collect(Collectors.toList());
        return postDTOS;
    }
}