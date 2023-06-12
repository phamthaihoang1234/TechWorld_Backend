package com.example.TechWorld.repository;

import com.example.TechWorld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByStatusTrue();

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String username);

    User findByToken(String token);

}
