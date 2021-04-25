package com.ak.service;

import com.ak.dto.CountryInfo;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.exception.WorkflowException;

import java.util.List;

public interface SService {
    LeagueInfo getLeagueStandings(String leagueName) throws WorkflowException;

    List<TeamInfo> getTeamStandings(String teamName) throws WorkflowException;

    CountryInfo getCountryStandings(String  countryName) throws WorkflowException;
}
