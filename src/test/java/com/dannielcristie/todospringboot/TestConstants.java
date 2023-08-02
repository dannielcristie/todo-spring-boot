package com.dannielcristie.todospringboot;

import java.util.ArrayList;
import java.util.List;

import com.dannielcristie.todospringboot.entities.Todo;

public class TestConstants {
    public static final List<Todo> TODOS = new ArrayList<>() {
        {
            add(new Todo(1001L, "@dannielcristie", "study java", false, 10));
            add(new Todo(2002L, "@dannielcristie", "study spring-boot", false, 10));
            add(new Todo(3003L, "@dannielcristie", "study spring-security", false, 10));
            add(new Todo(4004L, "@dannielcristie", "study Jpa", false, 10));
            add(new Todo(5005L, "@dannielcristie", "study Hibernate", false, 10));
        }
    };


    public static final Todo TODO = TODOS.get(0);
}
