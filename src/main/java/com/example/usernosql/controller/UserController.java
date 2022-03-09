package com.example.usernosql.controller;

import com.example.usernosql.models.UserModel;
import com.example.usernosql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public HttpEntity<?> getUsers() {
        List<UserModel> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/users/{id}")
    public HttpEntity<?> getUser(@PathVariable(value = "id") String id) {
        UserModel user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public HttpEntity<?> addUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/users/{id}")
    public HttpEntity<?> editUser(@PathVariable(value = "id") String userId, @RequestBody UserModel user) {
        UserModel updatedUser = userService.editUser(userId, user);
        return ResponseEntity.status(updatedUser != null ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public HttpEntity<?> deleteUser(@PathVariable(value = "id") String userId) {
        boolean deleted = userService.deleteUser(userId);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("user Deleted") : ResponseEntity.status(HttpStatus.CONFLICT).body("user not Deleted");
    }
}
