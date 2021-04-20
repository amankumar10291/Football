package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "league_teams")
public class LeagueTeams extends HibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int teamId;
    @NotNull
    private int leagueId;
    private String season;

}
