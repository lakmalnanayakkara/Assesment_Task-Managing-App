package com.taskManagerApp.backend.controller;

import com.taskManagerApp.backend.dto.StandardResponse;
import com.taskManagerApp.backend.dto.request.TaskRequestDTO;
import com.taskManagerApp.backend.dto.response.TaskResponseDTO;
import com.taskManagerApp.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(path = "/add-task")
    public ResponseEntity<StandardResponse> addTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.addTask(taskRequestDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(201,"SUCCESS",taskResponseDTO),
                HttpStatus.CREATED
        );
        return response;
    }

    @PutMapping(path = "/update-task", params = "id")
    public ResponseEntity<StandardResponse> updateTask(@RequestBody TaskRequestDTO taskRequestDTO, @RequestParam(value = "id") Long taskId){
        TaskResponseDTO taskResponseDTO = taskService.updateTask(taskRequestDTO,taskId);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(200,"SUCCESS",taskResponseDTO),
                HttpStatus.OK
        );
        return response;
    }

    @DeleteMapping(path = "/delete-task", params = "id")
    public ResponseEntity<StandardResponse> deleteTask(@RequestParam(value = "id") Long taskId){
        TaskResponseDTO taskResponseDTO = taskService.deleteTask(taskId);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(200,"SUCCESS",taskResponseDTO),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(path = "/get-task-by-id", params = "id")
    public ResponseEntity<StandardResponse> getTaskById(@RequestParam(value = "id") Long taskId){
        TaskResponseDTO taskResponseDTO = taskService.getTaskById(taskId);
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(200,"SUCCESS",taskResponseDTO),
                HttpStatus.OK
        );
        return response;
    }
}
