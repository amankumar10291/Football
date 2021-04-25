package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
public class League extends HibernateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int numOfTeams;
    private int countryId;
    private String countryname;
    @Pattern(regexp = "[12][0-9]{3}")
    private String founded;
}
