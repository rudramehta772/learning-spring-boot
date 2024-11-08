package com.project.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project.dao.UserRepository;
import com.project.project.models.Users;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers()
    {
        List<Users> users  = (List<Users>)userRepository.findAll();
        System.out.println(users+"************");
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/user-registration")
    public ResponseEntity<Users> createUser(@RequestBody Users user)
    {
        try {
            Users saved_user  = this.userRepository.save(user);
            System.out.println(saved_user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved_user);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
