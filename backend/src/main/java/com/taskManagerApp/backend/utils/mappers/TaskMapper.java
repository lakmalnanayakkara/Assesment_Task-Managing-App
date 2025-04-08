package com.taskManagerApp.backend.utils.mappers;

import com.taskManagerApp.backend.dto.request.TaskRequestDTO;
import com.taskManagerApp.backend.dto.response.TaskResponseDTO;
import com.taskManagerApp.backend.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "userId", target = "user.userId")
    Task taskRequestDTOToTask(TaskRequestDTO taskRequestDTO);

    @Mapping(source = "user.userId", target = "userId")
    TaskResponseDTO taskToTaskResponseDTO(Task task);

    List<TaskResponseDTO> taskListToTaskResponseDTOList(List<Task> tasks);
}
