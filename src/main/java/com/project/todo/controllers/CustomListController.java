package com.project.todo.controllers;

import com.project.todo.models.CustomList;
import com.project.todo.repositories.CustomListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Arrays;
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
			String name = list.getName().trim();
			list.setName(name);
			list = customListRepository.save(list);
			success = true;
			return new ResponseEntity<>(name, HttpStatus.CREATED);
		} finally {
			if (!success) {
				while (!success) {
					try {
						String str = list.getName().trim();
						if (str.contains(" ")) {

						    String[] arrOfStr = str.split(" ");
						    String lastItem = arrOfStr[arrOfStr.length - 1];
						    char c = lastItem.charAt(0);
						    String s = Character.toString(c);
						    if (s.equals("(")) {
						      String[] newArr = Arrays.copyOf(arrOfStr, arrOfStr.length-1);
						      String joinedString = String.join(" ", newArr);
						      str = joinedString.trim();
						    } else {
						      String joinedString = String.join(" ", arrOfStr);
						      str = joinedString;
						    }

						}
						list.setName(str + " (" + Integer.toString(index) + ")");
						index = index + 1;
						list = customListRepository.save(list);
						success = true;
						String name = list.getName();
						return new ResponseEntity<>(name, HttpStatus.CREATED);
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
