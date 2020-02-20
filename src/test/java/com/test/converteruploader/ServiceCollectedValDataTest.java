package com.test.converteruploader;

import com.test.converteruploader.model.entity.ValCurs;
import com.test.converteruploader.model.entity.Valute;
import com.test.converteruploader.service.ServiceCollectedValData;
import com.test.converteruploader.service.ServiceGettingAndSaveData;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Parameterized.UseParametersRunnerFactory
public class ServiceCollectedValDataTest {

    @Autowired
    public ServiceCollectedValData serviceCollectedValData;
    @Autowired
    private ServiceGettingAndSaveData saveDataService;

    @Test
    public void gettingCurrencyData()  throws IOException {

        Mono<String> result = serviceCollectedValData.gettingCurrencyData();
        ValCurs val = serviceCollectedValData.whenJavaGotFromXmlStr(result.block());
        List<Valute> valute = val.getValute();
        System.out.println(val);
        saveDataService.ValCursSave(val);
//        saveDataService.ValuteSave(valute);
    }
}