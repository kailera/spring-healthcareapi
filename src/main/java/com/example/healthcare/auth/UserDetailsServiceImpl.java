package com.example.healthcare.auth;

import com.example.healthcare.auth.UserDetailsImpl;
import com.example.healthcare.model.User;
import com.example.healthcare.repository.UserResponsitory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    private final UserResponsitory userResponsitory;

    public UserDetailsServiceImpl(UserResponsitory userResponsitory){
        this.userResponsitory = userResponsitory;
    }

    @Override
    public UserDetails loadUserByUsername (String email){
        User user = userResponsitory.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("User doesnt exists"));

        return new UserDetailsImpl(user);
    }
}
