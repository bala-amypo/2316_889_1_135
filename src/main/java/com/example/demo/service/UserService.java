package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    User registerUserEntity(User user);

    User getUserById(Long id);

    List<User> getAllUsers();
}
