package com.example.lab03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.lab03.Task;
import com.example.lab03.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping({"", "/"})
    public List<Task> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> findById(@PathVariable Long id) {
        Optional<Task> task = service.findById(id);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return task;
    }

    @GetMapping("/count/{keyword}") 
    public Long countByTitleContains(@PathVariable String keyword) { 
        return service.countByTitleContaining(keyword); 
    }

    @GetMapping("/find/{keyword}") 
    public Map<String, Object> searchByTitleContaining( 
        @PathVariable String keyword, 
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "2") int size) 
    { 
        Pageable paging = PageRequest.of(page, size, Sort.by("title").ascending()); 
        Page<Task> result = service.searchByTitleContaining(keyword, paging);
        Map<String, Object> response = new HashMap<String, Object>(); 
        response.put("content", result.getContent()); 
        response.put("currentPage", result.getNumber()); 
        response.put("totalPages", result.getTotalPages()); 
        response.put("totalItems", result.getTotalElements()); 
        return response; 
    }

    @GetMapping("/query/{keyword}") 
    public List<Task> findByQuery(@PathVariable String keyword) { 
        return service.findByQuery(keyword); 
    }
}