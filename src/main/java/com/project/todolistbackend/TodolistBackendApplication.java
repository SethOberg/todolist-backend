package com.project.todolistbackend;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.repositories.PersonRepository;
import com.project.todolistbackend.repositories.TodoListItemRepository;
import com.project.todolistbackend.repositories.TodoListRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@OpenAPIDefinition
@SpringBootApplication
public class TodolistBackendApplication implements ApplicationRunner {
	private PersonRepository personRepository;
	private TodoListRepository todoListRepository;

	private TodoListItemRepository todoListItemRepository;

	@Autowired
	public TodolistBackendApplication(PersonRepository personRepository, TodoListRepository todoListRepository, TodoListItemRepository todoListItemRepository) {
		this.personRepository = personRepository;
		this.todoListRepository = todoListRepository;
		this.todoListItemRepository = todoListItemRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(TodolistBackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Person person = new Person("Seth");
		person.setUuid(UUID.randomUUID());
		TodoList todoList = new TodoList(false, "Grocery shopping");
		todoList.setPerson(person);
		todoList.setId(UUID.randomUUID());
		TodoListItem todoListItem1 = new TodoListItem("apples", false);
		todoListItem1.setTodoList(todoList);
		TodoListItem todoListItem2 = new TodoListItem("bananas", false);
		todoListItem2.setTodoList(todoList);
		TodoListItem todoListItem3 = new TodoListItem("carrots", false);
		todoListItem3.setTodoList(todoList);
		TodoListItem todoListItem4 = new TodoListItem("dragonfruit", false);
		todoListItem4.setTodoList(todoList);

		todoList.addTodoListItem(todoListItem1);
		todoList.addTodoListItem(todoListItem2);
		todoList.addTodoListItem(todoListItem3);
		todoList.addTodoListItem(todoListItem4);

		person.addTodoList(todoList);
		personRepository.save(person);
	}
}
