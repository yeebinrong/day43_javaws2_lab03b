package com.example.lab03;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Long countByTitleContaining(String title);
    Page<Task> findByTitleContaining(String title, Pageable pageable);
    
    @Query(value="SELECT * FROM task WHERE title LIKE %:title%", nativeQuery=true) 
    List<Task> findByQuery(String title);
}