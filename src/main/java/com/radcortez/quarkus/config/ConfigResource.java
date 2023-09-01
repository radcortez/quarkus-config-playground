package com.radcortez.quarkus.config;

import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/config")
public class ConfigResource {
    @Inject
    SmallRyeConfig config;
    @ConfigProperty(name = "foo")
    String foo;

    @GET
    @Path("/{name}")
    public Response get(@PathParam("name") String name) {
        return Response.ok().entity(config.getConfigValue(name)).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prop")
    public String helloProp() {
        return foo;
    }
}
