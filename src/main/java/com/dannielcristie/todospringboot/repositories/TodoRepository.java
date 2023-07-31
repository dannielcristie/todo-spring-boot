package com.dannielcristie.todospringboot.repositories;

import com.dannielcristie.todospringboot.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo,Long>{
}
