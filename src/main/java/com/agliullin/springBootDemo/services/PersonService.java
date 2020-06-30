package com.agliullin.springBootDemo.services;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public void addNewPerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
