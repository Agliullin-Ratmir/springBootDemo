package com.agliullin.springBootDemo.controllers;

import com.agliullin.springBootDemo.entities.Person;
import com.agliullin.springBootDemo.entities.Role;
import com.agliullin.springBootDemo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.agliullin.springBootDemo.utils.EncrytedPasswordUtils.encryptePassword;

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

    @RequestMapping(value = "/logout")
    public String logout(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
    public String addNewUserGET(Map<String, Object> model) {
        return "addNewUser";
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public String addNewUserPOST(Map<String, Object> model,
                                 @RequestParam String name, @RequestParam String password,
                                 @RequestParam String role) {
        String encryptedPassword = encryptePassword(password);
        service.addNewPerson(getNewPerson(name, encryptedPassword, role));
        model.put("name", name);
        model.put("password", encryptedPassword);
        model.put("role", role);
        return "welcome";
    }

    @GetMapping("/error")
    public String loginError(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
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
