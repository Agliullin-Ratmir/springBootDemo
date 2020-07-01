package com.agliullin.springBootDemo.controllers;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.entities.Role;
import com.agliullin.springBootDemo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private PersonService service;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Map<String, Object> model,
                            @RequestParam String name, @RequestParam String password) {
        model.put("name", name);
        return "welcome";
    }

    @RequestMapping(value = "/getUsers")
    public String getUsers(Map<String, Object> model) {
        return "getUsers";
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
    public String addNewUserGET(Map<String, Object> model) {
        return "addNewUser";
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public String addNewUserPOST(Map<String, Object> model,
                                 @RequestParam String name, @RequestParam String password,
                                 @RequestParam String role) {

        service.addNewPerson(getNewPerson(name, password, role));
        model.put("name", name);
        model.put("password", password);
        model.put("role", role);
        return "welcome";
    }

    private Person getNewPerson(String name, String password, String role) {
        Person person = new Person();
        person.setLogin(name);
        person.setPassword(password);
        if ("ADMIN".equals(role)) {
            person.setRole(Role.ADMIN);
        } else {
            person.setRole(Role.USER);
        }
        return person;
    }
}
