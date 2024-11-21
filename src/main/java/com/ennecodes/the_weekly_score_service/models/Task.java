package com.ennecodes.the_weekly_score_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    @JsonBackReference
    private Routine routine;

    public Task(){}

    public Task(String taskName, Routine routine) {
        this.name = taskName;
        this.routine = routine;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Routine getRoutine() {
        return routine;
    }
}
