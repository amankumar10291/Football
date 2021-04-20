package com.ak.Resource;

import com.ak.entity.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @POST
    @Path("/add")
    public Response addPlayer(Player player){
        return Response.ok().build();
    }

    @GET
    @Path("/getById/{playerId}")
    public Response getPlayerById(@PathParam("playerId") Integer playerId) {
        return Response.ok().build();
    }

    @GET
    @Path("/getByName/{playerName}")
    public Response getPlayerByName(@PathParam("playerName") String playerName) {
        return Response.ok().build();
    }

    @GET
    @Path("/getAll")
    public Response getAllPlayer() {
        return Response.ok().build();
    }

}
