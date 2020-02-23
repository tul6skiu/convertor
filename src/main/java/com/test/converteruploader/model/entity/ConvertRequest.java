package com.test.converteruploader.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertRequest {
    private Integer count;
    private String currentName;
    private String targetName;
    private BigDecimal result;
}
