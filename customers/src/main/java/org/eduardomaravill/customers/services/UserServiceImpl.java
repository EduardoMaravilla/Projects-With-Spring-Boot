package org.eduardomaravill.customers.services;

import com.google.common.hash.Hashing;
import org.eduardomaravill.customers.entities.User;
import org.eduardomaravill.customers.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    private static final String SECRET_KEY = "prtn982@dfdf";
    @Autowired
    private IUserRepository userRepository;

    //get user by id
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for(User user : userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

    @Override
    public void registerUser(User user) {
        String hasPassword = Hashing.sha256().
                hashString(user.getPassword() + SECRET_KEY, StandardCharsets.UTF_8).toString();
        user.setPassword(hasPassword);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);
        userRepository.save(user);

    }

    @Override
    public List<User> searchUsers(String email, String address) {
        return userRepository.findByEmailOrAddress(email,address);
    }
}
