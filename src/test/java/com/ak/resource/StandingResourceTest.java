package com.ak.resource;

import com.ak.Resource.StandingResource;
import com.ak.dto.CountryInfo;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.exception.WorkflowException;
import com.ak.service.StandingService;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(DropwizardExtensionsSupport.class)
public class StandingResourceTest {

    private static final StandingService STANDING_SERVICE = mock(StandingService.class);
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new StandingResource(STANDING_SERVICE))
            .build();
    private TeamInfo teamInfo;
    private LeagueInfo leagueInfo;
    private CountryInfo countryInfo;

    @BeforeEach
    public void setUp() {
        teamInfo = new TeamInfo();
        teamInfo.setTeamName("LIVERPOOL");
        teamInfo.setTeamId(1);
        teamInfo.setMatchesPlayed(38);
        teamInfo.setMatchesLoss(2);
        teamInfo.setMatchesWon(34);
        teamInfo.setMatchesDrawn(2);
        teamInfo.setPosition(1);
        leagueInfo = new LeagueInfo();
        leagueInfo.setLeagueId(1);
        leagueInfo.setLeagueName("EPL");
        leagueInfo.setTeamInfos(Collections.singletonList(teamInfo));
        countryInfo = new CountryInfo();
        countryInfo.setCountryId(1);
        countryInfo.setCountryName("ENGLAND");
        countryInfo.setLeagueInfoList(Collections.singletonList(leagueInfo));
    }

    @AfterEach
    public void tearDown() {
        reset(STANDING_SERVICE);
    }

    @Test
    public void getByTeam() throws WorkflowException {
        final List<TeamInfo> teamInfos = Collections.singletonList(teamInfo);
        when(STANDING_SERVICE.getTeamStandings("LIVERPOOL")).thenReturn(teamInfos);

        List<TeamInfo> response = RESOURCES.target("/standing/getByTeam/LIVERPOOL").request(MediaType.APPLICATION_JSON).get(new GenericType<List<TeamInfo>>() {
        });

        verify(STANDING_SERVICE).getTeamStandings("LIVERPOOL");
        assertThat(response).containsAll(teamInfos);
    }

    @Test
    public void getByTeamNotFound() {
        when(STANDING_SERVICE.getTeamStandings("LEEDS")).thenThrow(new WorkflowException(Response.Status.NOT_FOUND,"Invalid Team"));

        Response response = RESOURCES.target("/standing/getByTeam/LEEDS").request().get();

        verify(STANDING_SERVICE).getTeamStandings("LEEDS");
        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void getByLeague() throws WorkflowException {
        when(STANDING_SERVICE.getLeagueStandings("EPL")).thenReturn(leagueInfo);

        LeagueInfo response = RESOURCES.target("/standing/getByLeague/EPL").request().get(LeagueInfo.class);

        verify(STANDING_SERVICE).getLeagueStandings("EPL");
        assertThat(response.getLeagueId()).isEqualTo(1);
        assertThat(response.getLeagueName()).isEqualTo("EPL");
        assertThat(response.getTeamInfos()).contains(teamInfo);
    }


    @Test
    public void getByLeagueNotFound() throws WorkflowException {
        when(STANDING_SERVICE.getLeagueStandings("ECL")).thenThrow(new WorkflowException(Response.Status.NOT_FOUND,"Invalid League"));

        Response response = RESOURCES.target("/standing/getByLeague/ECL").request().get();

        verify(STANDING_SERVICE).getLeagueStandings("ECL");
        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void getByCountry() throws WorkflowException {
        when(STANDING_SERVICE.getCountryStandings("ENGLAND")).thenReturn(countryInfo);

        CountryInfo response = RESOURCES.target("/standing/getByCountry/ENGLAND").request().get(CountryInfo.class);

        verify(STANDING_SERVICE).getCountryStandings("ENGLAND");
        assertThat(response.getCountryId()).isEqualTo(1);
        assertThat(response.getCountryName()).isEqualTo("ENGLAND");
        assertThat(response.getLeagueInfoList()).contains(leagueInfo);
    }


    @Test
    public void getByCountryNotFound() throws WorkflowException {
        when(STANDING_SERVICE.getCountryStandings("INDIA")).thenThrow(new WorkflowException(Response.Status.NOT_FOUND,"Invalid Country"));

        Response response = RESOURCES.target("/standing/getByCountry/INDIA").request().get();

        verify(STANDING_SERVICE).getCountryStandings("INDIA");
        assertThat(response.getStatusInfo().getStatusCode()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }



}
