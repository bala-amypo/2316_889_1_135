package com.example.project.service;

import com.example.project.entity.User;
import java.util.List;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    User findById(Long id);

    List<User> getAllUsers();
}
