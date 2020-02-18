package com.test.converteruploader;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Parameterized.UseParametersRunnerFactory
public class ServiceCollectedValDataTest {

    @Autowired
    public ServiceCollectedValData serviceCollectedValData;

    @Test
    void gettingCurrencyData() {
        serviceCollectedValData.gettingCurrencyData();
    }
}