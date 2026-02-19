package io.decisionflow.decisions.resource;

import io.decisionflow.decisions.model.Decision;
import io.decisionflow.groups.model.Group;
import io.decisionflow.options.Option;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/decisions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DecisionResource {

    @POST
    public Response createDecision(Decision decision, @QueryParam("groupId") Integer groupId) {
        Group group = Group.findById(groupId);
        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Group not found").build();
        }

        decision.group = group;
        decision.persist();
        return Response.status(Response.Status.CREATED).entity(decision).build();
    }

    @POST
    @Path("{decisionId}/options")
    public Response addOption(@PathParam("decisionId") Integer decisionId, Option option) {
        Decision decision = Decision.findById(decisionId);
        if (decision == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Decision not found").build();
        }

        option.decision = decision;
        option.persist();
        return Response.status(Response.Status.CREATED).entity(option).build();
    }

    @GET
    public List<Decision> getAllDecisions() {
        return Decision.listAll();
    }
}
