package com.test.converteruploader.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
    Users findByActivationCode(String code);
}
