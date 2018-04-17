package com.stnd.stndapi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Relation implements Serializable{

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name="userId",
                    column=@Column(name="user_id")),
            @AttributeOverride(name="watchedId",
                    column=@Column(name="watched_id"))
    })
    private RelationPK relationPK;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="watched_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User watchedUser;

    public Relation() {
    }

    public Relation(RelationPK pk) {
        super();
        this.relationPK = pk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getWatchedUser() {
        return watchedUser;
    }

    public void setWatchedUser(User watchedUser) {
        this.watchedUser = watchedUser;
    }

    public RelationPK getRelationPK() {
        return relationPK;
    }

    public void setRelationPK(RelationPK relationPK) {
        this.relationPK = relationPK;
    }

}
