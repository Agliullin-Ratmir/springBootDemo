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
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootDemoApplicationTests {

	private String LOGIN = "User1";
	private String PASSWORD = "Password1";

	@Autowired
	private PersonService service;

	@Test
	public void contextLoads() {
		Person person = getPerson(LOGIN, PASSWORD, Role.USER);
		service.addNewPerson(person);

		List<Person> list = service.findAll();

		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(LOGIN, person.getLogin());
		assertEquals(PASSWORD, person.getPassword());
		assertEquals(Role.USER, person.getRole());
	}

	@Test
	public void JLTest() {
		saveSquad(getSquad());

		List<Person> squad = null;
		assertFalse(isSquadFilled(squad));

		squad = service.findAll();
		assertTrue(isSquadFilled(squad));

		Long adminsAmount = squad.stream().filter(person -> Role.ADMIN.equals(person.getRole())).count();
		Long usersAmount = squad.stream().filter(person -> Role.USER.equals(person.getRole())).count();
		assertEquals(2, adminsAmount);
		assertEquals(5, usersAmount);
	}

	private boolean isSquadFilled(List<Person> list) {
		Optional<List<Person>> listOptional = Optional.ofNullable(list);
		return listOptional.isPresent();
	}

	private void saveSquad(List<Person> list) {
		list.stream().forEach(person -> {
			service.addNewPerson(person);
			System.out.println(String.format("Person %s has been added",
					person.getLogin()));
		});
	}

	private List<Person> getSquad() {
		List<Person> list = new ArrayList<>();
		list.add(getPerson("Batman", "pass1", Role.ADMIN));
		list.add(getPerson("Superman", "pass2", Role.ADMIN));
		list.add(getPerson("Flash", "pass3", Role.USER));
		list.add(getPerson("Lantern", "pass4", Role.USER));
		list.add(getPerson("Wonderwoman", "pass5", Role.USER));
		list.add(getPerson("Cyborg", "pass6", Role.USER));
		list.add(getPerson("Hunter", "pass7", Role.USER));
		return list;
	}

	private Person getPerson(String login, String password, Role role) {
		Person person = new Person();
		person.setLogin(login);
		person.setPassword(password);
		person.setRole(role);
		return person;
	}
}
