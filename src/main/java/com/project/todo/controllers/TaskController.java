package com.project.todo.controllers;

import com.project.todo.models.Task;
import com.project.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://leana2.dev", maxAge = 3600)
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



    @PostMapping(value = "/add",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Task task) throws URISyntaxException
    {
        task = taskRepository.save(task);
        return new ResponseEntity<>(task,  HttpStatus.CREATED);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTask(@RequestBody Task task)
    {
        task = taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable long taskId)
    {
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
