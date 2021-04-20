package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Stadium extends HibernateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String nickname;
    private int capacity;
    private Date createdAt;
}
