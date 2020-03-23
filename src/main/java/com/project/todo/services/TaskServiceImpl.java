//package com.project.todo.services;
//
//import com.project.todo.models.Task;
//import com.project.todo.repositories.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service(value = "taskService")
//public class TaskServiceImpl implements TaskService
//{
//    @Autowired
//    private TaskRepository taskRepository;
//
//
//    @Override
//    public List<Task> findAllByUser_id_fkEquals() {
//        return taskRepository.findAllByUser_id_fkEquals();
//    }
//}
