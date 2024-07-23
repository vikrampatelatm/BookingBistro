package com.hashedin.huSpark.service;


import com.hashedin.huSpark.dao.RegisterUserDto;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.hashedin.huSpark.model.RoleEnum;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.MANAGER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("user not found"));
    }
}
