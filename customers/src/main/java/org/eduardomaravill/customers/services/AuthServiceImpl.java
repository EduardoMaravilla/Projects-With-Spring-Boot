package org.eduardomaravill.customers.services;

import com.google.common.hash.Hashing;
import org.eduardomaravill.customers.entities.User;
import org.eduardomaravill.customers.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class AuthServiceImpl implements IAuthService{

    private static final String SECRET_KEY = "prtn982@dfdf";

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User login(String email, String password) {
        String hashPassword = Hashing.sha256()
                .hashString(password + SECRET_KEY, StandardCharsets.UTF_8)
                .toString();
        return userRepository.findByEmailAndPassword(email, hashPassword);
    }
}
