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
@IdClass(HistoryId.class)
public class History {
    @Id
    private UUID id;
    @Id
    private String sourceCur;
    @Id
    private String targetCur;
    @Id
    private Integer sourceValue;
    @Id
    private BigDecimal targetValue;
    private String userName;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="history_id", updatable = false, referencedColumnName = "id")
    private Valute valute;

}
