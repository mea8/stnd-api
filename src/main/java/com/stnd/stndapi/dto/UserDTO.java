package com.stnd.stndapi.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private long id;
    private String username;
    private List<String> watched = new ArrayList<>();
    private List<String> watching = new ArrayList<>();
    private List<String> posts = new ArrayList<>();

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

    public List<String> getWatched() {
        return watched;
    }

    public void setWatched(List<String> watched) {
        this.watched = watched;
    }

    public List<String> getWatching() {
        return watching;
    }

    public void setWatching(List<String> watching) {
        this.watching = watching;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }
}
