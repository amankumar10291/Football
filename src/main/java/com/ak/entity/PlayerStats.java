package com.ak.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Player player;
    @OneToOne
    private Game game;
    private int goalScored;
    private int yellowCard;
    private int redCard;
    private String season;
}
