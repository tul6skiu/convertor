package com.test.converteruploader.model;

public interface UserService {
    Users findUserByEmail(String email);
    void saveUser(Users user);
}
