package com.hashedin.huSpark.repository;

import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}