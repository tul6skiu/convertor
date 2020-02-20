package com.test.converteruploader.repository;

import com.test.converteruploader.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
    Users findByActivationCode(String code);
}
