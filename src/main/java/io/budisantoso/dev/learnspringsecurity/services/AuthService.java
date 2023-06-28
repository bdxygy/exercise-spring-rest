package io.budisantoso.dev.learnspringsecurity.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    boolean matching(String password, UserDetails userDetails);
}
