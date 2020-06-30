package com.agliullin.springBootDemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes("name")
public class LoginController {

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

    @RequestMapping(value = "/addNewUser")
    public String addNewUser(Map<String, Object> model) {
        return "addNewUser";
    }
}
