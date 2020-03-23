package com.project.todo.controllers;

import com.project.todo.models.Category;
import com.project.todo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> list()
    {
        return categoryRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Category get(@PathVariable Long id)
    {
        return categoryRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody final Category category)
    {
        return categoryRepository.saveAndFlush(category);
    }

}
