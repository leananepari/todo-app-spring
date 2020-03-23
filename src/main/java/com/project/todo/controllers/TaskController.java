package com.project.todo.controllers;

import com.project.todo.models.Task;
import com.project.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/all/{user_id}")
    public List<Task> getTasksByUser(@PathVariable Integer user_id)
    {
        return taskRepository.findTasks(user_id);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Task get(@PathVariable Long id)
    {
        return taskRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody final Task task)
    {
        return taskRepository.saveAndFlush(task);
    }
}
