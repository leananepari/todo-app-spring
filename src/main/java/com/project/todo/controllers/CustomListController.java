package com.project.todo.controllers;

import com.project.todo.models.CustomList;
import com.project.todo.repositories.CustomListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://leana2.dev", maxAge = 3600)
@RestController
@RequestMapping("/api/lists")
public class CustomListController {
	
    @Autowired
    private CustomListRepository customListRepository;


    @GetMapping(value = "/all/{user_id}")
    public List<CustomList> getListsByUser(@PathVariable Integer user_id)
    {
        return customListRepository.findLists(user_id);
    }

    @GetMapping
    @RequestMapping("{id}")
    public CustomList get(@PathVariable Long id)
    {
        return customListRepository.getOne(id);
    }

    @PostMapping(value = "/add",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody CustomList list) throws URISyntaxException
    {
    	boolean success = false;
    	int index = 1;

		try {
			
			list = customListRepository.save(list);
			success = true;
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} finally {
			if (!success) {
				while (!success) {
					try {
						String[] arrOfStr = list.getName().split(" ");
						list.setName(arrOfStr[0] + " (" + Integer.toString(index) + ")");
						index = index + 1;
						list = customListRepository.save(list);
						success = true;
						return new ResponseEntity<>(null, HttpStatus.CREATED);
					} finally {
						if (!success) {							
							continue;
						}
					}
				}
			}
		}
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateList(@RequestBody CustomList list)
    {
        list = customListRepository.save(list);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{listId}")
    public ResponseEntity<?> deleteList(@PathVariable long listId)
    {
    	customListRepository.deleteById(listId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
