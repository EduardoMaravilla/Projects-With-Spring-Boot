package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.User;

import java.util.List;

public interface IUserService {
    User getUser(Long id);
    List<User> getAllUsers();
    void registerUser(User user);
    void deleteUser(Long id);
    void updateUser(Long id, User user);
    List<User> searchUsers(String email, String address);
}
