package com.dannielcristie.todospringboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannielcristie.todospringboot.entities.Todo;
import com.dannielcristie.todospringboot.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    ResponseEntity<Todo> create(@RequestBody Todo obj) {
        obj = todoService.create(obj);
        return ResponseEntity.ok().body(obj);
    }

    

}
