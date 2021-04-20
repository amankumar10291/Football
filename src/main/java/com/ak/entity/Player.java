package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Player extends HibernateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String nickname;
    private Date dob;
    private String mailId;
    @OneToOne
    @JoinColumn(name = "birth_place")
    private City bornPlace;
}
