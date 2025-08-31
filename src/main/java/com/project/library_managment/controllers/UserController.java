package com.project.library_managment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.library_managment.Services.UserService;
import com.project.library_managment.models.User;

@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired 
	private UserService userService;

	 // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    
    // Fetch all the Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
