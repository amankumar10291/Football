package com.ak.dto;

import lombok.Data;

@Data
public class TeamInfo implements Comparable<TeamInfo> {

    private Integer teamId;
    private String teamName;
    private Integer matchesPlayed;
    private Integer matchesWon;
    private Integer matchesLoss;
    private Integer matchesDrawn;
    private Integer goalScored;
    private Integer goalFaced;
    private Integer points;
    private Integer position;
    private Integer leagueId;
    private String season;

    @Override
    public int compareTo(TeamInfo ob) {
        return this.getPosition() - ob.getPosition();
    }
}
