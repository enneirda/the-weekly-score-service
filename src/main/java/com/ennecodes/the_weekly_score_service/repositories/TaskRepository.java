package com.ennecodes.the_weekly_score_service.repositories;

import com.ennecodes.the_weekly_score_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
