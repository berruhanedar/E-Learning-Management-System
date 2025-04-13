package com.berru.app.elearningmanagementsystem.service;


import java.util.List;
import java.util.Optional;

import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public User addUser(User user) {
        return UserRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return UserRepository.save(user);
    }

    @Override
    public User getUserByEmailAndStatus(String emailId, String status) {
        return UserRepository.findByEmailIdAndStatus(emailId, status);
    }

    @Override
    public User getUserByEmailid(String emailId) {
        return UserRepository.findByEmailId(emailId);
    }

    @Override
    public List<User> getUserByRole(String role) {
        return UserRepository.findByRole(role);
    }

    @Override
    public User getUserById(int userId) {

        Optional<User> optionalUser = this.UserRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }

    }

    @Override
    public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
        return this.UserRepository.findByEmailIdAndRoleAndStatus(emailId, role, status);
    }

    @Override
    public List<User> updateAllUser(List<User> users) {
        return this.UserRepository.saveAll(users);
    }

    @Override
    public List<User> getUserByRoleAndStatus(String role, String status) {
        return this.UserRepository.findByRoleAndStatus(role, status);
    }
}