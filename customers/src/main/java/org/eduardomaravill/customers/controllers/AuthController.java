package org.eduardomaravill.customers.controllers;

import org.eduardomaravill.customers.dto.RequestLogin;
import org.eduardomaravill.customers.entities.User;
import org.eduardomaravill.customers.services.IAuthService;
import org.eduardomaravill.customers.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        User user = authService.login(requestLogin.getEmail(), requestLogin.getPassword());
        return JwtUtil.generateToken(user);
    }
}
