package com.ak.entity;

import com.ak.dao.HibernateEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TeamStanding extends HibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int teamId;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLoss;
    private int matchesDrawn;
    private int goalScored;
    private int goalFaced;
    private int points;
    private int position;
    private int leagueId;
    private String season;
}
