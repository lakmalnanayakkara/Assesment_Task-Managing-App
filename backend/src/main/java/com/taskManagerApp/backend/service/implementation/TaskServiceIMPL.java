package com.taskManagerApp.backend.service.implementation;

import com.taskManagerApp.backend.dto.request.TaskRequestDTO;
import com.taskManagerApp.backend.dto.response.TaskResponseDTO;
import com.taskManagerApp.backend.entity.Task;
import com.taskManagerApp.backend.exception.AlreadyExistException;
import com.taskManagerApp.backend.exception.NotFoundException;
import com.taskManagerApp.backend.repository.TaskRepository;
import com.taskManagerApp.backend.service.TaskService;
import com.taskManagerApp.backend.utils.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceIMPL implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findTaskByTitle(taskRequestDTO.getTitle());
        if (task == null) {
            Task newTask = taskMapper.taskRequestDTOToTask(taskRequestDTO);
            taskRepository.save(newTask);
            TaskResponseDTO responseDTO = taskMapper.taskToTaskResponseDTO(newTask);
            return responseDTO;
        }else {
            throw new AlreadyExistException("Task title already exists.");
        }
    }

    @Override
    public TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, Long taskId) {
        Task task = taskRepository.findTaskByTaskId(taskId);
        if (task != null) {
            task.setTitle(taskRequestDTO.getTitle());
            task.setDescription(taskRequestDTO.getDescription());
            task.setStatus(taskRequestDTO.getStatus());
            task.setCreated(taskRequestDTO.getCreated());
            Task updatedTask = taskRepository.save(task);
            TaskResponseDTO responseDTO = taskMapper.taskToTaskResponseDTO(updatedTask);
            return responseDTO;
        }else{
            throw new NotFoundException("Task not found.");
        }
    }

    @Override
    public TaskResponseDTO deleteTask(Long taskId) {
        Task task = taskRepository.findTaskByTaskId(taskId);
        if (task != null) {
            TaskResponseDTO responseDTO = taskMapper.taskToTaskResponseDTO(task);
            taskRepository.delete(task);
            return responseDTO;
        }else {
            throw new NotFoundException("Task not found.");
        }
    }

    @Override
    public TaskResponseDTO getTaskById(Long taskId) {
        Task task = taskRepository.findTaskByTaskId(taskId);
        if (task != null) {
            TaskResponseDTO responseDTO = taskMapper.taskToTaskResponseDTO(task);
            return responseDTO;
        }else {
            throw new NotFoundException("Task not found.");
        }
    }
}
