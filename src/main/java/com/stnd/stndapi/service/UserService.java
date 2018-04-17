package com.stnd.stndapi.service;

import com.stnd.stndapi.converter.ObjectConverter;
import com.stnd.stndapi.dao.UserDAO;
import com.stnd.stndapi.dto.PostDTO;
import com.stnd.stndapi.dto.UserDTO;
import com.stnd.stndapi.entity.Relation;
import com.stnd.stndapi.entity.RelationPK;
import com.stnd.stndapi.entity.User;
import com.stnd.stndapi.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    public User create(User user) {
        return userDao.create(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        System.out.println("\n1.findAll()...");
        List<User> users =  userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(long id){
        User user = userDao.findById(id);
        return ObjectConverter.mapUserToUsetDTO(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void followUser(long userId, long followed) {
        User user = userDao.findById(userId);
        if(user == null){
            throw new UserNotFound(userId);
        }

        User followedUser = userDao.findById(followed);
        if(followedUser == null){
            throw new UserNotFound(followed);
        }

        Relation relation =  new Relation(new RelationPK(user.getId(),followedUser.getId()));
        userDao.follow(relation);
    }
}