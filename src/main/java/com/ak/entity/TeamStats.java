package com.ak.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TeamStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Team team;
    @OneToOne
    private Game game;
    private ResultType result;
    private int goalScored;
    private int goalFaced;
    private int yellowCard;
    private int redCard;
    private String season;

}
