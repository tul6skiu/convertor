package com.test.converteruploader.repository;

import com.test.converteruploader.model.entity.Valute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.Validator;

public interface ValuteRepository extends JpaRepository<Valute, String> {
    Valute findValuteByName(String name);
}
