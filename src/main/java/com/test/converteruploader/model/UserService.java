package com.test.converteruploader.model;

import com.test.converteruploader.model.entity.Users;

public interface UserService {
    Users findUserByEmail(String email);
    void saveUser(Users user);
    boolean isActivateUser(String code);
    boolean addUser(Users user);
}
