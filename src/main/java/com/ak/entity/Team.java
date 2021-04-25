package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    private String nickName;
    @Pattern(regexp = "[12][0-9]{3}")
    private String founded;
    private TeamType teamType;
    @ManyToOne
    @JoinColumn(name = "city")
    private City cityId;
    @OneToOne
    @JoinColumn(name = "homeGround")
    private Stadium homeGround;

}
