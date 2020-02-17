package com.test.converteruploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public History(Long id, String sourceCur, String targetCur, Integer sourceValue, BigDecimal targetValue, Date date, Users user) {
        this.id = id;
        this.sourceCur = sourceCur;
        this.targetCur = targetCur;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
        this.date = date;
        this.user = user;
    }
}
