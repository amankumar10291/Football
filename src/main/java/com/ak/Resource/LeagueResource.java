package com.ak.Resource;

import com.ak.entity.League;
import com.ak.entity.LeagueTeams;
import com.ak.service.LeagueService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/league")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeagueResource {

    private LeagueService leagueService;

    @Inject
    public LeagueResource(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @POST
    @Path("/addLeague")
    public Response addLeague(League league) {
        return Response.ok().build();
    }

    @POST
    @Path("/addTeam")
    public Response addTeam(LeagueTeams leagueTeams) {
        return Response.ok().build();
    }

    @GET
    @Path("/getById/{leagueId}")
    public Response getPlayerById(@PathParam("leagueId") Integer leagueId) {

        leagueService.getLeagueDetails(leagueId);
        return Response.ok().build();
    }

    @GET
    @Path("/getByName/{leagueName}")
    public Response getPlayerByName(@PathParam("leagueName") String leagueName) {
        return Response.ok().build();
    }

    @GET
    @Path("/getAll")
    public Response getAllPlayer() {
        return Response.ok().build();
    }
}
