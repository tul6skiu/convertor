package com.test.converteruploader.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "valute", schema = "converter")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(exclude = "valCurs")

public class Valute {
    @Id
    @JsonProperty("ID")
    private String id;
    @JsonProperty("NumCode")
    private Integer NumCode;
    @JsonProperty("CharCode")
    private String CharCode;
    @JsonProperty("Nominal")
    private Integer Nominal;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Value")
    private String Value;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="date", updatable = false, referencedColumnName = "date")
    private ValCurs valCurs;

}
