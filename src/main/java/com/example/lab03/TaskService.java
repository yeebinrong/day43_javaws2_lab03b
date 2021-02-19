package com.example.lab03;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(Long id) {
        Optional<Task> task = repository.findById(id);
        return task;
    }

    public Long countByTitleContaining(String title) { 
        return repository.countByTitleContaining(title); 
    }

    public Page<Task> searchByTitleContaining(String title, Pageable pageable) { 
        return repository.findByTitleContaining(title, pageable); 
    }

    public List<Task> findByQuery(String title) { 
        return repository.findByQuery(title); 
    }
}