package com.example.userservice.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseModel {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
