package com.taskManagerApp.backend.service;


import com.taskManagerApp.backend.dto.request.TaskRequestDTO;
import com.taskManagerApp.backend.dto.response.TaskResponseDTO;

public interface TaskService {

    TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO);

    TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, Long taskId);

    TaskResponseDTO deleteTask(Long taskId);

    TaskResponseDTO getTaskById(Long taskId);
}
