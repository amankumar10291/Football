package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class League extends HibernateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int countryId;
    private String countryname;
    private Date startedOn;
}
