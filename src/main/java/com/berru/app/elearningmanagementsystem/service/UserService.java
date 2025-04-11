package com.berru.app.elearningmanagementsystem.service;

import java.util.List;

import com.berru.app.elearningmanagementsystem.entity.User;

public interface UserService {
    User addUser(User user);

    User updateUser(User user);

    User getUserByEmailAndStatus(String email, String status);

    User getUserByEmail(String email);

    List<User> getUserByRole(String role);

    User getUserById(int userId);

    User getUserByEmailAndRoleAndStatus(String email, String role, String status);

    List<User> updateAllUser(List<User> users);

    List<User> getUserByRoleAndStatus(String role, String status);
}
