package com.test.converteruploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "val_curs", schema = "convertor")
public class ValCurs {
    private Date date;
    private String name;
    List<Valute> valute;
}
