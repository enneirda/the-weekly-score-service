package com.ennecodes.the_weekly_score_service.dtos;

import com.ennecodes.the_weekly_score_service.models.Routine;
import org.springframework.stereotype.Component;

@Component
public class RoutineMapper {
    public Routine toEntity(CreateRoutineRequest dto){
        return new Routine(dto.getName(), dto.getTaskNames(), dto.getUserFirebaseId());
    }
}
