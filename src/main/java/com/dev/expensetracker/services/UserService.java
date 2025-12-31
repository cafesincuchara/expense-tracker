package com.dev.expensetracker.services;

import com.dev.expensetracker.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> createUsersList(List<User> users);
    List<User> findAll();
    User findById(UUID id);
    void deleteUser(UUID id);
}
