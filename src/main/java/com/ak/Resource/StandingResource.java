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
    @Path("/getByLeagueId/{leagueId}")
    public Response getByLeague(@PathParam("leagueId") int leagueId) {
        try {
            LeagueInfo leagueInfo = standingService.getLeagueStandings(leagueId);
            return Response.ok().entity(leagueInfo).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/getByTeamId/{teamId}")
    public Response getByTeam(@PathParam("teamId") int teamId) {
        try {
            List<TeamInfo> list = standingService.getTeamStandings(teamId);
            return Response.ok().entity(list).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/getByCountryId/{countryId}")
    public Response getByCountry(@PathParam("countryId") int countryId) {
        try {
            CountryInfo list = standingService.getCountryStandings(countryId);
            return Response.ok().entity(list).build();
        } catch (WorkflowException e) {
            return Response.status(e.getStatus()).entity("Message: " + e.getMessage()).build();
        }
    }


}
