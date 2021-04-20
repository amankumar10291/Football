package com.ak.service;

import com.ak.dao.LeagueDao;
import com.ak.dao.LeagueTeamsDao;
import com.ak.dao.StandingDao;
import com.ak.dao.TeamDao;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.entity.League;
import com.ak.entity.LeagueTeams;
import com.ak.entity.Team;
import com.ak.entity.TeamStanding;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class StandingServiceTest {

    private static final StandingDao STANDING_DAO = mock(StandingDao.class);
    private static final TeamDao TEAM_DAO = mock(TeamDao.class);
    private static final LeagueDao LEAGUE_DAO = mock(LeagueDao.class);
    private static final LeagueTeamsDao LEAGUE_TEAMS_DAO = mock(LeagueTeamsDao.class);

    private static final StandingService STANDING_SERVICE = mock(StandingService.class);

    private Team team;
    private TeamInfo teamInfo;
    private TeamStanding teamStanding;
    private League league;
    private LeagueTeams leagueTeams;
    private LeagueInfo leagueInfo;

    @BeforeEach
    public void setUp() {
        team = new Team();
        team.setId(1);
        team.setName("LIVERPOOL");
        teamInfo = new TeamInfo();
        teamInfo.setTeamName("LIVERPOOL");
        teamInfo.setTeamId(1);
        teamInfo.setMatchesPlayed(38);
        teamInfo.setMatchesLoss(2);
        teamInfo.setMatchesWon(34);
        teamInfo.setMatchesDrawn(2);
        teamInfo.setPosition(1);
        teamStanding = new TeamStanding();
        teamStanding.setTeamId(1);
        teamStanding.setMatchesPlayed(38);
        teamStanding.setMatchesLoss(2);
        teamStanding.setMatchesWon(34);
        teamStanding.setMatchesDrawn(2);
        teamStanding.setPosition(1);
        league = new League();
        league.setId(1);
        league.setCountryId(1);
        league.setCountryname("UK");
        league.setName("EPL");
        leagueTeams = new LeagueTeams();
        leagueTeams.setLeagueId(1);
        leagueTeams.setTeamId(1);
        leagueTeams.setSeason("2020-2021");
        leagueInfo = new LeagueInfo();
        leagueInfo.setLeagueId(1);
        leagueInfo.setLeagueName("EPL");
        leagueInfo.setTeamInfos(Collections.singletonList(teamInfo));
    }

    @AfterEach
    void tearDown() {
        reset(STANDING_DAO);
        reset(TEAM_DAO);
        reset(LEAGUE_DAO);
        reset(LEAGUE_TEAMS_DAO);
    }

    @Test
    public void getTeamStandings(){
        when(STANDING_SERVICE.getTeamStandings(1)).thenReturn(Collections.singletonList(teamInfo));
        when(TEAM_DAO.findById(1)).thenReturn(team);
        when(STANDING_DAO.findByTeamId(1)).thenReturn(Collections.singletonList(teamStanding));
        List<TeamInfo> list = STANDING_SERVICE.getTeamStandings(1);
        assertThat(list.get(0).getMatchesDrawn()).isEqualTo(2);
        assertThat(list.get(0).getMatchesWon()).isEqualTo(34);
        assertThat(list.get(0).getMatchesPlayed()).isEqualTo(38);
    }

    @Test
    public void getLeagueStandings(){
        when(STANDING_SERVICE.getLeagueStandings(1)).thenReturn(leagueInfo);
        when(LEAGUE_DAO.findById(1)).thenReturn(league);
        when(LEAGUE_TEAMS_DAO.getTeamsByLeague(1)).thenReturn(Collections.singletonList(leagueTeams));
        when(TEAM_DAO.findById(1)).thenReturn(team);
        when(STANDING_DAO.findByTeamId(1)).thenReturn(Collections.singletonList(teamStanding));

        LeagueInfo list = STANDING_SERVICE.getLeagueStandings(1);
        assertThat(list.getLeagueName()).isEqualTo("EPL");
        assertThat(list.getLeagueId()).isEqualTo(1);
    }
}
