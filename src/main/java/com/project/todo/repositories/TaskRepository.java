package com.project.todo.repositories;

import com.project.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    @Query("select s from tasks s where s.user_id_fk = :user_id")
    List<Task> findTasks(@Param("user_id") Integer objectId);
}
