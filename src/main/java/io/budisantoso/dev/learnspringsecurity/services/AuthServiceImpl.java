package io.budisantoso.dev.learnspringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean matching(String password, UserDetails userDetails) {
        return passwordEncoder.matches(password, userDetails.getPassword());
    }
}
