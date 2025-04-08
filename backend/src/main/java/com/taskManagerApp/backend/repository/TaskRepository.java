package com.taskManagerApp.backend.repository;

import com.taskManagerApp.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByTitle(String title);

    Task findTaskByTaskId(Long taskId);
}
