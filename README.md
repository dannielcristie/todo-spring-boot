<h1 align="center">
  TODO List
</h1>


API para gerenciar tarefas (CRUD) que faz parte [desse desafio](https://github.com/simplify-liferay/desafio-junior-backend-simplify) para pessoas desenvolvedoras backend júnior, que se candidatam para a Simplify.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- API REST
- SOLID,
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Teste automatizados

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Executar a aplicação:
```
java -jar target/todo-spring-boot-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

The API provides the following endpoints:

```markdown
POST /todos - Criar Tarefa.

GET /todos/{id} - Listar uma tarefas por id.

GET /todos - Listar todas as tarefas.

PUT /todos/{id} - Atualizar uma tarefa por id.

DELETE /todos/{id} - Remover uma tarefa.
```

##
<a href = "https://www.linkedin.com/in/dannielcristie/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>