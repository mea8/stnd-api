package com.stnd.stndapi.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name="find_posts_by_userId", query="select p from Post p where p.user.id = :id order by p.date desc"),
        @NamedQuery(name="find_posts_for_userId", query="select p from Post p join fetch p.user u " +
                "join fetch u.relations r where r.relationPK.userId = :id order by p.date desc")
})
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "created_on")
    private LocalDateTime date;

    public Post() {
    }

    public Post(User user, String text, LocalDateTime date) {
        super();
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
