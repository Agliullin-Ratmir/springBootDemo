package com.agliullin.springBootDemo;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.entities.Role;
import com.agliullin.springBootDemo.repositories.PersonRepository;
import com.agliullin.springBootDemo.services.PersonService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootDemoApplicationTests {

	private String LOGIN = "User1";
	private String PASSWORD = "Password1";

	@Autowired
	private PersonService service;

	@Test
	public void contextLoads() {
		Person person = getPerson();
		service.addNewPerson(person);

		List<Person> list = service.findAll();

		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(LOGIN, person.getLogin());
		assertEquals(PASSWORD, person.getPassword());
		assertEquals(Role.USER, person.getRole());
	}

	private Person getPerson() {
		Person person = new Person();
		person.setLogin(LOGIN);
		person.setPassword(PASSWORD);
		person.setRole(Role.USER);
		return person;
	}

}
