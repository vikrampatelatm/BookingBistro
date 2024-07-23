package com.hashedin.huSpark.bootstrap;


import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import com.hashedin.huSpark.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;


    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.CUSTOMER, RoleEnum.MANAGER};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.CUSTOMER, "Default user role",
                RoleEnum.MANAGER, "Administrator role"

        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);

                roleRepository.save(roleToCreate);
            });
        });
    }
}