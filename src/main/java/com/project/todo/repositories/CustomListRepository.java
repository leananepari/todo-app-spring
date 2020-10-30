package com.project.todo.repositories;

import java.util.List;

import com.project.todo.models.CustomList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomListRepository extends JpaRepository<CustomList, Long>{
    @Query("select s from custom_lists s where s.user_id_fk = :user_id")
    List<CustomList> findLists(@Param("user_id") Integer objectId);
}


