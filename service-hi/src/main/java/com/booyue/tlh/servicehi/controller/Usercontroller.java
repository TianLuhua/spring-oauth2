package com.booyue.tlh.servicehi.controller;

import com.booyue.tlh.servicehi.dao.User;
import com.booyue.tlh.servicehi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Usercontroller {

    @Autowired
    protected UserService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return service.create(username, password);
    }
}