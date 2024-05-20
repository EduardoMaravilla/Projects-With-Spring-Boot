package org.eduardomaravill.customers.controllers;

import org.eduardomaravill.customers.entities.User;
import org.eduardomaravill.customers.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/user/{id}")//Get user
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")//Get users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")//Add user
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/user/{id}")//Delete user
    public void deleteCustomer(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")//Update user
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @GetMapping("/users/search")//Search users
    public List<User> searchUsers(@RequestParam(name = "email", required = false) String email
            , @RequestParam(name = "address", required = false) String address) {
        return userService.searchUsers(email, address);
    }

}
