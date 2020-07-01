package com.agliullin.springBootDemo.services;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByLogin(s);
        String role = "ROLE_" + person.getRole().name();
        List<GrantedAuthority> grantList = Collections.singletonList(
                new SimpleGrantedAuthority(role));
        UserDetails userDetails = (UserDetails) new User(person.getLogin(),
                person.getPassword(), grantList);
        return userDetails;
    }
}
