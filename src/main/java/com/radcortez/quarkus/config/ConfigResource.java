package com.radcortez.quarkus.config;

import io.quarkus.redis.datasource.RedisDataSource;
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
    @Inject
    RedisDataSource redisDataSource;

    @GET
    @Path("/{name}")
    public Response get(@PathParam("name") String name) {
        System.out.println(config.getConfigValue("quarkus.redis.password"));
        System.out.println(config.getConfigValue("AUTH_CONSENT_REDIS_PASSWORD"));
        System.out.println(config.getConfigValue("auth.consent.redis.password"));
        return Response.ok().entity(config.getConfigValue(name)).build();
    }
}
