package org.eduardomaravill.customers.services;

import org.eduardomaravill.customers.entities.User;

public interface IAuthService {

    User login(String email, String password);
}
