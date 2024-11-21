package com.ennecodes.the_weekly_score_service.dtos;

import java.util.List;

public class CreateRoutineRequest {
    private String name;
    private List<String> taskNames;
    private String userFirebaseId;

    public String getName () {
        return this.name;
    }

    public List<String> getTaskNames () {
        return this.taskNames;
    }

    public String getUserFirebaseId(){
        return this.userFirebaseId;
    }
}

