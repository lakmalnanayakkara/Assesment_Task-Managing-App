package com.taskManagerApp.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponseDTO {
    private int taskId;
    private String title;
    private String status;
    private LocalDateTime created;
    private String description;
    private Long userId;
}
