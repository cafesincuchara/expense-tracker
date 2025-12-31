package com.dev.expensetracker.features.user.repository;

import com.dev.expensetracker.features.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByName(String username);
}
