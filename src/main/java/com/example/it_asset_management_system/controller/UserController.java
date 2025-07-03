package com.example.it_asset_management_system.controller;

import com.example.it_asset_management_system.entity.User;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;
import com.example.it_asset_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create a user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        //User addUser(String username, String email, String password,String role);
        User addedUser=userService.addUser(user.getUsername(),user.getEmail(),user.getPassword(),
                user.getRole().toString());

        return ResponseEntity.ok(addedUser);
    }

    //get All user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user=userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //update user
    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user) {
        //User updateUser(int id,String username, String email, String password, String role)throws Exception;
        User updatedUser;
        try {
            updatedUser = userService.updateUser(id, user.getUsername(), user.getEmail(), user.getPassword(),
                    user.getRole().toString());
            return ResponseEntity.ok(updatedUser);
        }catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if(userService.deleteUser(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
