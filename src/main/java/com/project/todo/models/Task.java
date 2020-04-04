package com.project.todo.models;

import com.project.todo.logging.Loggable;

import javax.persistence.*;
import java.sql.Timestamp;

@Loggable
@Entity(name = "tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String description;
    private Timestamp created;
    private Timestamp due;
    private boolean completed = false;
    private boolean important = false;
    private Integer user_id_fk;
    private Integer category_id_fk;

    public Task() {
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getDue() {
        return due;
    }

    public void setDue(Timestamp due) {
        this.due = due;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Integer getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(Integer user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    public Integer getCategory_id_fk() {
        return category_id_fk;
    }

    public void setCategory_id_fk(Integer category_id_fk) {
        this.category_id_fk = category_id_fk;
    }
}
