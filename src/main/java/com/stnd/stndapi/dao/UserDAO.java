package com.stnd.stndapi.dao;

import com.stnd.stndapi.entity.Relation;
import com.stnd.stndapi.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public User create(User user) {
        return entityManager.merge(user);
    }

    public User update(User user) {
        return entityManager.merge(user);
    }

    public User findById(long id) {
        TypedQuery<User> typedQuery = entityManager.createNamedQuery(
                "find_user_by_id",User.class);
        List<User> users = typedQuery.setParameter("id",id).getResultList();

        if (users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    public List<User> findAll() {
        TypedQuery<User> typedQuery = entityManager.createNamedQuery(
                "find_all_users", User.class);

        return typedQuery.getResultList();
    }

    public void deleteById(long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public Relation follow(Relation relation) {
        return entityManager.merge(relation);
    }
}
