package com.test.converteruploader.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class HistoryId implements Serializable {
    private UUID id;
    private String sourceCur;
    private String targetCur;
    private Integer sourceValue;
    private BigDecimal targetValue;
}
