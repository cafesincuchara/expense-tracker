package com.dev.expensetracker.features.user.service;

import com.dev.expensetracker.features.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> createUsersList(List<User> users);
    List<User> findAll();
    User findById(UUID id);
    void deleteUser(UUID id);
}
