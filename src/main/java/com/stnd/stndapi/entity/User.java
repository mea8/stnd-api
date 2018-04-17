package com.stnd.stndapi.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQueries({
        @NamedQuery(name="find_all_users", query="select u from User u"),
        @NamedQuery(name="find_user_by_id", query="select u from User u where u.id = :id")
})
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "watchedUser", fetch = FetchType.LAZY)
    private Set<Relation> relations = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Relation> users = new HashSet<>();

    public User() {
    }

    public User(long id, String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public User(String username) {
        super();
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Relation> getRelations() {
        return relations;
    }

    public void setRelations(Set<Relation> relations) {
        this.relations = relations;
    }

    public Set<Relation> getUsers() {
        return users;
    }

    public void setUsers(Set<Relation> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}