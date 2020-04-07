package com.project.todo.controllers;

import com.project.todo.models.Task;
import com.project.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @CrossOrigin(origins = "https://to-do-app-five.now.sh")
    @GetMapping(value = "/all/{user_id}")
    public List<Task> getTasksByUser(@PathVariable Integer user_id)
    {
        return taskRepository.findTasks(user_id);
    }

    @CrossOrigin(origins = "https://to-do-app-five.now.sh")
    @GetMapping
    @RequestMapping("{id}")
    public Task get(@PathVariable Long id)
    {
        return taskRepository.getOne(id);
    }

    @CrossOrigin(origins = "https://to-do-app-five.now.sh")
    @PostMapping(value = "/add",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Task task) throws URISyntaxException
    {
        task = taskRepository.save(task);
        return new ResponseEntity<>(null,  HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "https://to-do-app-five.now.sh")
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTask(@RequestBody Task task)
    {
        task = taskRepository.save(task);
        return new ResponseEntity<>(null,  HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://to-do-app-five.now.sh")
    @DeleteMapping(value = "/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable long taskId)
    {
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
