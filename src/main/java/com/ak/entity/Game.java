package com.ak.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;
    @OneToOne
    private League league;
    @OneToOne
    private Stadium stadium;
    private int homeTeamScored;
    private int awayTeamScored;
    private int yellowCard;
    private int redCard;
    private String season;
    private Date playedOn;

}
