package com.berru.app.elearningmanagementsystem.repository;

import com.berru.app.elearningmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);  // 'emailId' yerine 'email' kullanılmalı

    User findByEmailAndStatus(String email, String status);  // 'emailId' yerine 'email' kullanılmalı

    User findByRoleAndStatusIn(String role, List<String> status);

    List<User> findByRole(String role);

    User findByEmailAndRoleAndStatus(String email, String role, String status);  // 'emailId' yerine 'email' kullanılmalı

    List<User> findByRoleAndStatus(String role, String status);
}
