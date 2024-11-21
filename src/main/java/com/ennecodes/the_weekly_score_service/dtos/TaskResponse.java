package com.ennecodes.the_weekly_score_service.dtos;

public class TaskResponse {

    private Long id;
    private String name;
    private boolean isCompleted;

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIsCompleted (boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public boolean getIsCompleted(){
        return this.isCompleted;
    }

}
