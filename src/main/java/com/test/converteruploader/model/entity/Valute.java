package com.test.converteruploader.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
    private String name;
    @JsonProperty("Value")
    private String Value;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="date", updatable = false, referencedColumnName = "date")
    private ValCurs valCurs;

    @OneToMany(mappedBy = "valute", cascade = CascadeType.ALL)
    private List<History> history;


}
