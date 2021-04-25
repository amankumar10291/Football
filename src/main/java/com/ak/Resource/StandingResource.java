package com.ak.Resource;

import com.ak.dto.CountryInfo;
import com.ak.dto.LeagueInfo;
import com.ak.dto.TeamInfo;
import com.ak.exception.WorkflowException;
import com.ak.service.StandingService;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/standing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StandingResource {

    private StandingService standingService;

    @Inject
    public StandingResource(StandingService standingService) {
        this.standingService = standingService;
    }

    @GET
    @UnitOfWork
    @Path("/getByLeague/{leagueName}")
    public Response getByLeague(@PathParam("leagueName") String leagueName) {
        try {
            LeagueInfo leagueInfo = standingService.getLeagueStandings(leagueName);
            return Response.ok().entity(leagueInfo).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/getByTeam/{team}")
    public Response getByTeam(@PathParam("team") String teamName) {
        try {
            List<TeamInfo> list = standingService.getTeamStandings(teamName);
            return Response.ok().entity(list).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/getByCountry/{country}")
    public Response getByCountry(@PathParam("country") String country) {
        try {
            CountryInfo list = standingService.getCountryStandings(country);
            return Response.ok().entity(list).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }


}
