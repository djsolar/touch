package com.twinflag.touch.service;


import com.twinflag.touch.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
