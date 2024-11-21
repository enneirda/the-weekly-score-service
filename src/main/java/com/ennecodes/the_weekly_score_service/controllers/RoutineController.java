package com.ennecodes.the_weekly_score_service.controllers;

import com.ennecodes.the_weekly_score_service.dtos.CreateRoutineRequest;
import com.ennecodes.the_weekly_score_service.dtos.RoutineMapper;
import com.ennecodes.the_weekly_score_service.dtos.RoutineResponse;
import com.ennecodes.the_weekly_score_service.dtos.TaskResponse;
import com.ennecodes.the_weekly_score_service.models.Routine;
import com.ennecodes.the_weekly_score_service.models.Task;
import com.ennecodes.the_weekly_score_service.models.TaskCompletion;
import com.ennecodes.the_weekly_score_service.repositories.RoutineRepository;
import com.ennecodes.the_weekly_score_service.repositories.TaskCompletionRepository;
import com.ennecodes.the_weekly_score_service.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class RoutineController {
    private final RoutineRepository routineRepository;
    private final TaskCompletionRepository taskCompletionRepository;
    private final TaskRepository taskRepository;


    @Autowired
    private RoutineMapper routineMapper;

    public RoutineController(RoutineRepository routineRepository, TaskCompletionRepository taskCompletionRepository, TaskRepository taskRepository){
        this.routineRepository = routineRepository;
        this.taskCompletionRepository = taskCompletionRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("routines/{id}")
    public ResponseEntity<RoutineResponse> getRoutineById(@PathVariable Long id){
        return routineRepository.findById(id).map(this::mapToRoutineResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/routines")
    public List<RoutineResponse> getAllRoutines() {

        return routineRepository.findAll().stream().map(this::mapToRoutineResponse).collect(Collectors.toList());

    }

    @PostMapping(value = "/routines")
    public RoutineResponse createRoutine(@RequestBody CreateRoutineRequest request){
        Routine routine = routineMapper.toEntity(request);
        Routine newRoutine = routineRepository.save(routine);
        return mapToRoutineResponse(newRoutine);
    }

    @PatchMapping("/tasks/{id}/incomplete")
    public TaskResponse markTaskIncomplete(@PathVariable Long id) throws Exception{
        LocalDate today = LocalDate.now();
        Optional<TaskCompletion> existingCompletion = taskCompletionRepository
                .findByTaskIdAndDate(id, today);

        if (existingCompletion.isPresent()){
            TaskCompletion taskCompletion = existingCompletion.get();
            taskCompletion.setCompletedTime(null);
            taskCompletion.setIsComplete(false);
            taskCompletionRepository.save(taskCompletion);
            return mapTaskResponse(taskCompletion.getTask(), false);
        } else {
            throw new Exception("Can't mark nonexistent task incomplete" + id);
        }

    }

    @PatchMapping("/tasks/{id}/complete")
    public TaskResponse markTaskComplete(@PathVariable Long id) throws Exception{
        LocalDate today = LocalDate.now();
        Timestamp timestamp = Timestamp.from(Instant.now());
        Optional<TaskCompletion> existingCompletion = taskCompletionRepository
                .findByTaskIdAndDate(id, today);

        if (existingCompletion.isPresent()){
            TaskCompletion taskCompletion = existingCompletion.get();
            taskCompletion.setCompletedTime(timestamp);
            taskCompletion.setIsComplete(true);
            taskCompletionRepository.save(taskCompletion);
            return mapTaskResponse(taskCompletion.getTask(), true);

        } else {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new Exception("Task not found with ID: " + id));
            TaskCompletion newTaskCompletion = new TaskCompletion(task, today, timestamp, true);
            taskCompletionRepository.save(newTaskCompletion);
            return mapTaskResponse(task, true);
        }

    }


    public RoutineResponse mapToRoutineResponse(Routine routine) {
        RoutineResponse response = new RoutineResponse();
        response.setId(routine.getId());
        response.setName(routine.getName());
        response.setTasks(
                routine.getTasks().stream()
                        .map(task -> {
                            TaskResponse taskResponse = new TaskResponse();
                            taskResponse.setId(task.getId());
                            taskResponse.setName(task.getName());
                            taskResponse.setIsCompleted(isCompleted(task.getId()));
                            return taskResponse;
                        })
                        .collect(Collectors.toList())
        );
        return response;
    }

    public TaskResponse mapTaskResponse(Task task, boolean isCompleted){
        TaskResponse response= new TaskResponse();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setIsCompleted(isCompleted);

        return response;

    }

    public Boolean isCompleted(Long taskId){
        LocalDate today = LocalDate.now();
        Optional<TaskCompletion> completion = taskCompletionRepository.findByTaskIdAndDate(taskId, today);
        return completion.isPresent() && completion.get().getIsComplete();
    }



}
