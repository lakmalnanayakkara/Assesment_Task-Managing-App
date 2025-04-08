package com.taskManagerApp.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskPaginatedResponseDTO {
    private List<TaskResponseDTO> tasks;
    private Long total;
}
