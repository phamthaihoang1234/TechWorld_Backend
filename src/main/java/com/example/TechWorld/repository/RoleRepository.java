package com.example.TechWorld.repository;

import com.example.TechWorld.common.ERole;
import com.example.TechWorld.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
