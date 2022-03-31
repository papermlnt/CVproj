package com.cvproject.cvProject.service;

import com.cvproject.cvProject.exception.UserNotFoundException;
import com.cvproject.cvProject.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cvproject.cvProject.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .sorted(Comparator.comparing(User::getFirstName))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
    }

    @Transactional(readOnly = true)
    public User updateUser(User userId) {
        User user = userRepository.findById(userId.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFirstName(user.getFirstName());
        user.setId(user.getId());
        user.setPassword(user.getPassword());
        user.setLastName(user.getLastName());
        user.setRoles(user.getRoles());

        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
