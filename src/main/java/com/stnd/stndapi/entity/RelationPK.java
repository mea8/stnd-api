package com.stnd.stndapi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RelationPK implements Serializable{

    private long userId;

    private long watchedId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getWatchedId() {
        return watchedId;
    }

    public void setWatchedId(long watchedId) {
        this.watchedId = watchedId;
    }

    public RelationPK(long userId, long watchedId) {
        this.userId = userId;
        this.watchedId = watchedId;
    }

    public RelationPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationPK that = (RelationPK) o;

        if (userId != that.userId) return false;
        return watchedId == that.watchedId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (int) (31 * result + userId);
        result = (int) (31 * result + watchedId);
        return result;
    }
}
