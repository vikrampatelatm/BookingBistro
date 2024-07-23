package com.hashedin.huSpark.service;


import com.hashedin.huSpark.dao.LoginUserDto;
import com.hashedin.huSpark.dao.RegisterUserDto;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signup(RegisterUserDto input) {

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.CUSTOMER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setRole(optionalRole.get())
                .setMobile(input.getMobile())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}