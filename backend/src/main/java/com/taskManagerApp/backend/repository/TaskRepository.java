package com.taskManagerApp.backend.repository;

import com.taskManagerApp.backend.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByTitle(String title);

    Task findTaskByTaskId(Long taskId);

    @Query(value = "select * from tasks t where t.user_id= :userId", nativeQuery = true)
    List<Task> getAllTasksByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "select count(*) from tasks t where t.user_id= :userId", nativeQuery = true)
    Long countAllTasksByUserId(@Param("userId") Long userId);
}
