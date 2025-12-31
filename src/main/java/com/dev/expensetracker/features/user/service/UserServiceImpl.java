package com.dev.expensetracker.features.user.service;

import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.common.exceptions.InvalidBusinessLogicException;
import com.dev.expensetracker.common.exceptions.ResourceNotFoundException;
import com.dev.expensetracker.features.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findById(user.getId()).isPresent()){
            throw new InvalidBusinessLogicException("User with id " + user.getId() + " already exists");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new InvalidBusinessLogicException("User with email " + user.getEmail() + " already exists");
        }
        if(userRepository.findByName(user.getName()).isPresent()){
            throw new InvalidBusinessLogicException("User with name " + user.getName() + " already exists");
        }
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public List<User> createUsersList(List<User> users) {
        users.forEach(user -> user.setCreatedAt(LocalDateTime.now()));
        return userRepository.saveAll(users);
    }


    @Override
    public List<User> findAll() {
        return  userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void deleteUser(UUID id) {
        User delete = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User not found: " + id ) );
        userRepository.delete(delete);
    }
}
