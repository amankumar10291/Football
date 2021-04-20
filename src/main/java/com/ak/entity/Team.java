package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Team extends HibernateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String code;
    private String name;
    private Date foundedAt;
    private TeamType teamType;
    @ManyToOne
    @JoinColumn(name = "city")
    private City cityId;
    @OneToOne
    @JoinColumn(name = "homeGround")
    private Stadium homeGround;

}
