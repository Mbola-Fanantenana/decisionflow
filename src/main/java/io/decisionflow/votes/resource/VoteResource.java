package io.decisionflow.votes.resource;

import io.decisionflow.options.model.Option;
import io.decisionflow.users.model.User;
import io.decisionflow.votes.model.Vote;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/votes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoteResource {

    @POST
    public Response vote(@QueryParam("userId") Integer userId, @QueryParam("optionId") Integer optionId) {
        User user = User.findById(userId);
        Option option = Option.findById(optionId);
        if (user == null || option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean alreadyVoted = Vote.list("option.decision = ?1 and user = ?2", option.decision, user).size() > 0;
        if (alreadyVoted) {
            return Response.status(Response.Status.CONFLICT).entity("User has already voted on this decision").build();
        }

        Vote vote = new Vote();
        vote.user = user;
        vote.option = option;
        vote.persist();

        return Response.status(Response.Status.CREATED).entity(vote).build();
    }

    @GET
    @Path("/results/{decisionId}")
    public Response getResults(@PathParam("decisionId") Integer decisionId) {
        Option.findById(decisionId);
        List<Option> options = Option.list("decision.id", decisionId);
        Map<String, Long> results = options.stream()
                .collect(Collectors.toMap(
                        option -> option.label,
                        option -> Vote.count("option", option)
                ));
        return Response.ok(results).build();
    }
}
