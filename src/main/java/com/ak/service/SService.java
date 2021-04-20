package com.ak.service;

import com.ak.dto.CountryInfo;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.exception.WorkflowException;

import java.util.List;

public interface SService {
    LeagueInfo getLeagueStandings(int leagueId) throws WorkflowException;

    List<TeamInfo> getTeamStandings(int teamId) throws WorkflowException;

    CountryInfo getCountryStandings(int countryId) throws WorkflowException;
}
