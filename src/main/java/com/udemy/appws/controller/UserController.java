package com.udemy.appws.controller;

import com.udemy.appws.bean.UserResponse;
import com.udemy.appws.entity.User;
import com.udemy.appws.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //http://localhost:8088/
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user/id/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @GetMapping(path = "/user/userID/{userID}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse findByUserID(@PathVariable String userID) {

        return this.userService.findByUserID(userID);
    }

    @GetMapping(path = "/users",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> findAll() {
        log.info("Getting all users");
        return this.userService.findAll();
    }


    @PostMapping(path = "/user")
    public UserResponse postUser(@RequestBody User user) {
        log.info("Saving a user");
        return this.userService.saveUser(user);
    }

    @DeleteMapping(path = "/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        log.info("Deleting a user by id=" + id);
        return "Delete was called";
    }

}
