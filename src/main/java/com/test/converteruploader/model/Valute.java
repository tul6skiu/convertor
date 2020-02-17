package com.test.converteruploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Table(name = "currency", schema = "convertor")
public class Valute {
    @Id
    private String ID;
    private Integer NumCode;
    private String CharCode;
    private Integer Nominal;
    private String Name;
    private BigDecimal Value;
}
