package com.taskManagerApp.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequestDTO {
    private String title;
    private String status;
    private LocalDateTime created;
    private String description;
    private Long userId;
}
