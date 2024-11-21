package com.ennecodes.the_weekly_score_service.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Task> tasks;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    private String userId;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Routine(){}

    public Routine(String name, List<String> taskNames, String userId) {
        this.name = name;
        this.userId = userId;
        this.tasks = new ArrayList<>();

        for (String taskName: taskNames) {
            Task task = new Task(taskName, this);
            this.tasks.add(task);
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
