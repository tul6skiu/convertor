package com.test.converteruploader.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history", schema = "converter")
public class History {
    @Id
    private UUID id;
    private String sourceCur;
    private String targetCur;
    private Integer sourceValue;
    private BigDecimal targetValue;
    private String userName;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private Valute valute;

}
