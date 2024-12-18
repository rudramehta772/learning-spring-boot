package com.project.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.project.dao.UserRepository;
import com.project.project.models.Users;
import com.project.project.services.UserService;
import com.project.project.utils.response.Response;



@RestController
@RequestMapping("api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUsers(){
        List<Users> users  = (List<Users>)userRepository.findAll();
        if (users.isEmpty()) {
            return Response.success(HttpStatus.NOT_FOUND);
        }
        return Response.success(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id){
        try {
            Users user = userService.getUser(id);
            if (user != null) {
                return Response.success(user);
            } else {
                return Response.error("User not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody Users user){
        try {
            if (userRepository.existsByUsername(user.getUsername()).get()) {
                return Response.error("user already exists");
            }
            Users saved_user  = this.userService.saveUser(user);
            System.out.println(saved_user);
            return Response.success(saved_user, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return Response.error();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> editUser(@PathVariable("id") Long id, @RequestBody Users user){
        if (userRepository.existsById(id)) {
            Users updated_user = userService.updateUser(id, user);
            return Response.success(updated_user);
        } else {
            return Response.error();
        }
    }

    @PostMapping("login")
    public ResponseEntity<Object> showPassword(@RequestBody Users user){
        Users auth_user = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (auth_user != null) {
            return Response.success("Login in success",auth_user);
        } else {
            return Response.error("Username and Password didn't match", HttpStatus.UNAUTHORIZED);
        }
    }
}