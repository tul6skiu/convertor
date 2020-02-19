package com.test.converteruploader.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "val_curs", schema = "converter")
public class ValCurs {
    @Id
    @JsonProperty("Date")
    private String date;

    public void setDate(String date) {

        this.date = date;
    }

    @JsonProperty("name")
    private String name;


    @JsonManagedReference
    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    @OneToMany(mappedBy = "valCurs", cascade = CascadeType.ALL)
    private List<Valute> valute;

    public ValCurs(List<Valute> valute) {
        this.valute = valute;
    }


}
