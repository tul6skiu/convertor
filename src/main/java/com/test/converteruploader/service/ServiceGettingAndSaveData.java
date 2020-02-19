package com.test.converteruploader.service;

import com.test.converteruploader.model.ValCurs;
import com.test.converteruploader.model.Valute;
import com.test.converteruploader.repository.ValCursRepository;
import com.test.converteruploader.repository.ValuteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ServiceGettingAndSaveData {
    private ValCursRepository valCursRepo;
    private ValuteRepository valuteRepo;

    @Autowired
    public ServiceGettingAndSaveData(ValCursRepository valCursRepo, ValuteRepository valuteRepo) {
        this.valCursRepo = valCursRepo;
        this.valuteRepo = valuteRepo;
    }

    public void ValCursSave(ValCurs valCurs) {
        valCursRepo.save(valCurs);
    }

    public void ValuteSave(List<Valute> valute) {
        valuteRepo.saveAll(valute);
    }
}
