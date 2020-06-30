package com.agliullin.springBootDemo.repositories;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * User can find another user, admins can find both users and admins
     * @param role
     * @return
     */
    List<Person> findPersonByRole(Role role);
}
