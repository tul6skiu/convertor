package com.test.converteruploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "valute", schema = "converter")
@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {
    @Id
    private String id;
    private Integer NumCode;
    private String CharCode;
    private Integer Nominal;
    private String Name;
    private BigDecimal Value;

    @ManyToOne
    @JoinColumn(name = "date", insertable = false, updatable = false)
    private ValCurs valCurs;

}
