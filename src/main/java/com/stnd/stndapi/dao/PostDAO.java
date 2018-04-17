package com.stnd.stndapi.dao;

import com.stnd.stndapi.entity.Post;
import com.stnd.stndapi.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PostDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Post create(Post post) {
        return entityManager.merge(post);
    }

    public List<Post> findByUserId(long id) {
        TypedQuery<Post> typedQuery = entityManager.createNamedQuery(
                "find_posts_by_userId",Post.class);
        List<Post> posts = typedQuery.setParameter("id",id).getResultList();
        return posts;
    }

    public List<Post> findForUserId(long userId) {
        TypedQuery<Post> typedQuery = entityManager.createNamedQuery(
                "find_posts_for_userId",Post.class);
        List<Post> posts = typedQuery.setParameter("id",userId).getResultList();
        return posts;
    }

}
