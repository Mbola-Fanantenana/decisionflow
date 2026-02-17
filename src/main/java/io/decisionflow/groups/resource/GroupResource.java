package io.decisionflow.groups.resource;

import io.decisionflow.groups.model.Group;
import io.decisionflow.users.model.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashSet;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupResource {

    @POST
    public Response createGroup(Group group) {
        group.members = new HashSet<>();
        group.persist();
        return Response.status(Response.Status.CREATED).entity(group).build();
    }

    @POST
    @Path("/{groupId}/add-member/{userId}")
    public Response addMember(@PathParam("groupId") Integer groupId, @PathParam("userId") Integer userId) {
        Group group = Group.findById(groupId);
        User user = User.findById(userId);
        if (group == null || user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        group.members.add(user);
        group.persist();
        return Response.ok(group).build();
    }

    @GET
    public Response getAllGroups() {
        return Response.ok(Group.listAll()).build();
    }
}
