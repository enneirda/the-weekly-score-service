package com.ennecodes.the_weekly_score_service.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name="task_completion")
public class TaskCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(nullable = false)
    private LocalDate date;

    private Timestamp completedTime;

    private boolean isComplete;

    public TaskCompletion(){}

    public TaskCompletion(Task task, LocalDate today, Timestamp timestamp, boolean isComplete) {
        this.task = task;
        this.date = today;
        this.completedTime = timestamp;
        this.isComplete = isComplete;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    public void setCompletedTime(Timestamp completedTime) {
        this.completedTime = completedTime;
    }

    public Task getTask() {
        return task;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }
}
