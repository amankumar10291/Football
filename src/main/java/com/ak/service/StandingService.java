package com.ak.service;

import com.ak.dao.LeagueDao;
import com.ak.dao.LeagueTeamsDao;
import com.ak.dao.StandingDao;
import com.ak.dao.TeamDao;
import com.ak.dto.CountryInfo;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.entity.League;
import com.ak.entity.LeagueTeams;
import com.ak.entity.Team;
import com.ak.entity.TeamStanding;
import com.ak.exception.WorkflowException;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class StandingService implements SService {

    private TeamDao teamDao;
    private StandingDao standingDao;
    private LeagueDao leagueDao;
    private LeagueTeamsDao leagueTeamsDao;

    @Inject
    public StandingService(TeamDao teamDao, StandingDao standingDao,
                           LeagueDao leagueDao, LeagueTeamsDao leagueTeamsDao) {
        this.teamDao = teamDao;
        this.standingDao = standingDao;
        this.leagueDao = leagueDao;
        this.leagueTeamsDao = leagueTeamsDao;
    }

    @Override
    public LeagueInfo getLeagueStandings(String leagueName) throws WorkflowException {
        League league = leagueDao.findByName(leagueName.toUpperCase());
        if (league == null)
            throw new WorkflowException(Response.Status.NOT_FOUND, "League Not found!!");

        List<LeagueTeams> teamIds = leagueTeamsDao.getTeamsByLeague(league.getId());
        if (teamIds == null || teamIds.size() == 0)
            throw new WorkflowException(Response.Status.NOT_FOUND, "No team in this league.");

        List<TeamInfo> teamInfos = new ArrayList<>();
        for (LeagueTeams id : teamIds) {
            teamInfos.addAll(getTeamStandings(id.getTeamId()));
        }
        Collections.sort(teamInfos);

        LeagueInfo leagueInfo = new LeagueInfo();
        leagueInfo.setLeagueId(league.getId());
        leagueInfo.setLeagueName(league.getName());
        leagueInfo.setTeamInfos(teamInfos);
        return leagueInfo;
    }

    @Override
    public List<TeamInfo> getTeamStandings(String teamName) throws WorkflowException {
        Team team = teamDao.findByName(teamName.toUpperCase());
        if (team == null)
            throw new WorkflowException(Response.Status.NOT_FOUND, "Team not found!!");
        List<TeamStanding> teamStandings = standingDao.findByTeamId(team.getId());
        return transformToTeamInfo(team, teamStandings);
    }

    public List<TeamInfo> getTeamStandings(int teamId) throws WorkflowException {
        Team team = teamDao.findById(teamId);
        if (team == null)
            throw new WorkflowException(Response.Status.NOT_FOUND, "Invalid team");
        List<TeamStanding> teamStandings = standingDao.findByTeamId(teamId);
        return transformToTeamInfo(team, teamStandings);
    }

    private List<TeamInfo> transformToTeamInfo(Team team, List<TeamStanding> teamStandings) {
        List<TeamInfo> output = new ArrayList<>();
        for (TeamStanding teamStanding : teamStandings) {
            TeamInfo teamInfo = new TeamInfo();
            teamInfo.setTeamId(team.getId());
            teamInfo.setTeamName(team.getName());
            teamInfo.setMatchesPlayed(teamStanding.getMatchesPlayed());
            teamInfo.setGoalFaced(teamStanding.getGoalFaced());
            teamInfo.setGoalScored(teamStanding.getGoalScored());
            teamInfo.setMatchesWon(teamStanding.getMatchesWon());
            teamInfo.setMatchesDrawn(teamStanding.getMatchesDrawn());
            teamInfo.setMatchesLoss(teamStanding.getMatchesLoss());
            teamInfo.setPoints(teamStanding.getPoints());
            teamInfo.setPosition(teamStanding.getPosition());
            teamInfo.setLeagueId(teamStanding.getLeagueId());
            teamInfo.setSeason(teamStanding.getSeason());
            output.add(teamInfo);
        }
        return output;
    }

    @Override
    public CountryInfo getCountryStandings(String name) throws WorkflowException {
        List<League> list = leagueDao.getLeaguesByCountry(name.toUpperCase());
        if (list == null || list.size() == 0)
            throw new WorkflowException(Response.Status.NOT_FOUND, "No leagues found for this country: " + name);

        List<LeagueInfo> leagueInfos = new ArrayList<>();
        for (League league : list) {
            try {
                LeagueInfo leagueInfo = getLeagueStandings(league.getName());
                leagueInfos.add(leagueInfo);
            }catch (WorkflowException ex) {
                LeagueInfo leagueInfo = new LeagueInfo();
                leagueInfo.setLeagueId(league.getId());
                leagueInfo.setLeagueName(league.getName());
                leagueInfos.add(leagueInfo);
                log.info("League: {} teams not found!!", league.getName());
            }
        }
        CountryInfo countryInfo = new CountryInfo();
        countryInfo.setCountryId(list.get(0).getId());
        countryInfo.setCountryName(list.get(0).getCountryname());
        countryInfo.setLeagueInfoList(leagueInfos);
        return countryInfo;
    }
}
