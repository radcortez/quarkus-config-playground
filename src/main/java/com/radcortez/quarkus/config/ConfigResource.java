package com.radcortez.quarkus.config;

import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/config")
public class ConfigResource {
    @Inject
    SmallRyeConfig config;

    @GET
    @Path("/{name}")
    public Response get(@PathParam("name") String name) {
        return Response.ok().entity(config.getConfigValue(name)).build();
    }
}
