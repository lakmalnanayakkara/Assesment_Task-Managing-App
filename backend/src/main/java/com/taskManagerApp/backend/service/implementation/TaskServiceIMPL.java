package com.taskManagerApp.backend.service.implementation;

import com.taskManagerApp.backend.repository.TaskRepository;
import com.taskManagerApp.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceIMPL implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
}
