# Back-End

# Login 

POST: https://to-do-app-spring.herokuapp.com/login (OAUTH2)<br>
GET: https://to-do-app-spring.herokuapp.com/users/getuserinfo

# Sign-up

POST: https://to-do-app-spring.herokuapp.com/createnewuser
```
{
    "username": "username",
    "password": "password",
    "primaryemail": "email@email.com"
}
```

# Endpoints

* Get all tasks by user id<br>
 GET: https://to-do-app-spring.herokuapp.com/api/tasks/all/{user_id}

* Get a task by id<br>
 GET: https://to-do-app-spring.herokuapp.com/api/tasks/{id}

* Post a task<br>
 POST: https://to-do-app-spring.herokuapp.com/api/tasks/add<br>
 ```
    {
      "description": "description,
      "category_id_fk": category id,
      "completed": false,
      "important": false,
      "created": date,
      "due": date
   }
 ```

* Edit a task<br>
 PUT: https://to-do-app-spring.herokuapp.com/api/tasks/update<br>
 ```
    {
      "description": "description,
      "category_id_fk": category id,
      "completed": false,
      "important": false,
      "created": date,
      "due": date
   }
 ```
 
* Delete a task <br>
 DELETE: https://to-do-app-spring.herokuapp.com/api/tasks/delete/{taskId}{id}

