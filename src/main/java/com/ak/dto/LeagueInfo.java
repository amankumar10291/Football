package com.ak.dto;

import com.ak.entity.TeamStanding;
import lombok.Data;

import java.util.List;

@Data
public class LeagueInfo {

    private int leagueId;
    private String leagueName;
    private List<TeamInfo> teamInfos;

}
