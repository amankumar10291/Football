package com.ak.dto;

import lombok.Data;

@Data
public class TeamInfo {

    private int teamId;
    private String teamName;
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
