package com.dannielcristie.todospringboot;

import static com.dannielcristie.todospringboot.TestConstants.TODO;
import static com.dannielcristie.todospringboot.TestConstants.TODOS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.dannielcristie.todospringboot.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
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

	@Sql("/import.sql")
	@Test
	void testFindAllTodosSucess() {
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0]").isEqualTo(TODOS.get(0))
				.jsonPath("$[1]").isEqualTo(TODOS.get(1))
				.jsonPath("$[2]").isEqualTo(TODOS.get(2))
				.jsonPath("$[3]").isEqualTo(TODOS.get(3))
				.jsonPath("$[4]").isEqualTo(TODOS.get(4));
	}

	@Test
	void testFindAllTodosEmpty() {
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(0);
	}

	@Sql("/import.sql")
	@Test
	void testUpdateTodoSucess() {
		var todo = new Todo(TODO.getId(), TODO.getName() + "new", TODO.getDescription() + "new", !TODO.getDone(),
				TODO.getPriority() - 1);

		webTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$.id").isEqualTo(todo.getId())
				.jsonPath("$.name").isEqualTo(todo.getName())
				.jsonPath("$.description").isEqualTo(todo.getDescription())
				.jsonPath("$.done").isEqualTo(todo.getDone())
				.jsonPath("$.priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testUpdateTodoFailure() {
		var unExistingId = 1L;
		var todo = new Todo(unExistingId, "", "", false, 0);
		webTestClient
				.put()
				.uri("/todos/" + unExistingId)
				.bodyValue(todo)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Sql("/import.sql")
	@Test
	void testDeleteTodoSucess() {
		webTestClient
				.delete()
				.uri("/todos/" + TODOS.get(0).getId())
				.exchange()
				.expectStatus().isNoContent();
	}

	@Test
	void testDeleteTodoFailure() {
		var unExistingId = 1L;

		webTestClient
				.delete()
				.uri("/todos/" + unExistingId)
				.exchange()
				.expectStatus().isNotFound();
	}

}
