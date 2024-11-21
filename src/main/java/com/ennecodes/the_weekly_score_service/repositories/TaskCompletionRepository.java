package com.ennecodes.the_weekly_score_service.repositories;

import com.ennecodes.the_weekly_score_service.models.TaskCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskCompletionRepository extends JpaRepository<TaskCompletion, Long> {

    Optional<TaskCompletion> findByTaskIdAndDate(Long taskId, LocalDate date);

}
