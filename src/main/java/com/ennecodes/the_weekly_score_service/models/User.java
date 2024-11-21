package com.ennecodes.the_weekly_score_service.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name="users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firebaseId;
    private String username;
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    public User(){}

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }


}
