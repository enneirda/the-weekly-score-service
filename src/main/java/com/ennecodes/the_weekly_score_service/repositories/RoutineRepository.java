package com.ennecodes.the_weekly_score_service.repositories;

import com.ennecodes.the_weekly_score_service.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findAllByUserId(String id);
}
