package com.example.userservice.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token extends BaseModel {

    @Column(nullable = false, unique = true)
    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryAt;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean deleted = false;


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Date expiryAt) {
        this.expiryAt = expiryAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
