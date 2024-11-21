package com.ennecodes.the_weekly_score_service.dtos;

import java.util.List;

public class RoutineResponse
{
    private Long id;
    private String name;
    private List<TaskResponse> tasks;


    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTasks (List<TaskResponse> tasks){
        this.tasks = tasks;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<TaskResponse> getTasks(){
        return this.tasks;
    }
}


