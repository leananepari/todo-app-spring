package com.project.todo.models;

import com.project.todo.logging.Loggable;

import javax.persistence.*;
import java.sql.Timestamp;

@Loggable
@Entity(name = "custom_lists")
public class CustomList {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custom_list_id;
    private String name;
    private Timestamp created;
    private Integer user_id_fk;
    private Integer theme_id_fk;
    

    public CustomList() {
    }

    public Long getList_id() {
        return custom_list_id;
    }

    public void setList_id(Long custom_list_id) {
        this.custom_list_id = custom_list_id;
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public Timestamp getCreated() {
    	return created;
    }
    public void setCreated(Timestamp created) {
    	this.created = created;
    }
    public Integer getUser_id_fk() {
    	return user_id_fk;
    }
    public void setUser_id_fk(Integer user_id_fk) {
    	this.user_id_fk = user_id_fk;
    }
    public Integer getTheme_id_fk() {
    	return theme_id_fk;
    }
    public void setTheme_id_fk(Integer theme_id_fk) {
    	this.theme_id_fk = theme_id_fk;
    }

}