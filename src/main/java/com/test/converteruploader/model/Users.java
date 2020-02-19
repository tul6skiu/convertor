package com.test.converteruploader.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "converter")
public class Users  {
    @Id
    private String Id;
    private String name;
    private String email;
    private String password;
    private String activationCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private History history;

}
