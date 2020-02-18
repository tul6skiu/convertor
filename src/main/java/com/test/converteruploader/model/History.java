package com.test.converteruploader.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history", schema = "converter")
public class History {
    @Id
    private Long id;
    private String sourceCur;
    private String targetCur;
    private Integer sourceValue;
    private BigDecimal targetValue;
    private Date date;

    @OneToOne(mappedBy = "history")
    private Users user;

}
