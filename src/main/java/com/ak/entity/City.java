package com.ak.entity;


import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "city")
public class City extends HibernateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country countryId;
}
