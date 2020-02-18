package com.test.converteruploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ValCursId.class)
@Table(name = "val_curs", schema = "converter")
//@XmlRootElement(name="ValCurs", namespace="ForeignCurrencyMarket")
//@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs {
    @Id
    private Date date;
    @Id
    private String name;

    @OneToMany
    @JoinColumn(name = "date")
    private List<Valute> valutes;

    public ValCurs(List<Valute> valutes) {
        this.valutes = valutes;
    }
}
