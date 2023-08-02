package com.dannielcristie.todospringboot.services;

import com.dannielcristie.todospringboot.entities.Todo;
import com.dannielcristie.todospringboot.repositories.TodoRepository;
import com.dannielcristie.todospringboot.services.exceptions.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        Sort sort = Sort.by("priority").descending().and(
                Sort.by("name").ascending());
        return todoRepository.findAll(sort);
    }

    public Todo findById(Long id) {
        Optional<Todo> obj = todoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Todo not found".formatted(id)));
    }

    public Todo update(Long id, Todo obj) {
        try {
            Todo entity = todoRepository.getReferenceById(id);
            updateData(entity, obj);
            return todoRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Todo not found".formatted(id));
        }
    }

    private void updateData(Todo entity, Todo obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPriority(obj.getPriority());
        entity.setDone(obj.getDone());
    }

    public void delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((entity) -> {
            todoRepository.deleteById(id);
        }, () -> {
            throw new ObjectNotFoundException("todo not found.".formatted(id));
        });
    }
}
