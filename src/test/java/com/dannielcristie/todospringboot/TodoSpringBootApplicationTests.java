package com.dannielcristie.todospringboot;

import static com.dannielcristie.todospringboot.TestConstants.TODO;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.dannielcristie.todospringboot.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodoSpringBootApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("Todo 1", "desc todo 1", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.name").isEqualTo(todo.getName())
				.jsonPath("$.description").isEqualTo(todo.getDescription())
				.jsonPath("$.done").isEqualTo(todo.getDone())
				.jsonPath("$.priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(new Todo("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	void testFindByIdTodoSucess() {
		var todo = new Todo(TODO.getId(), TODO.getName(), TODO.getDescription(), TODO.getDone(), TODO.getPriority());

		webTestClient
				.get()
				.uri("/todos/" + TODO.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.id").isEqualTo(todo.getId())
				.jsonPath("$.name").isEqualTo(todo.getName())
				.jsonPath("$.description").isEqualTo(todo.getDescription())
				.jsonPath("$.done").isEqualTo(todo.getDone())
				.jsonPath("$.priority").isEqualTo(todo.getPriority());

	}

	@Test
	void testFindByIdTodoFailure() {
		var unExistingId = 1L;
		webTestClient
				.get()
				.uri("/todos/", +unExistingId)
				.exchange()
				.expectStatus().isNotFound();

	}

	@Test
	void testFindAll() throws Exception {
		var obj = TestConstants.TODO;

	}

}
