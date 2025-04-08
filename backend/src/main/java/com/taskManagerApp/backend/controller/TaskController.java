package com.taskManagerApp.backend.controller;

import com.taskManagerApp.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
}
