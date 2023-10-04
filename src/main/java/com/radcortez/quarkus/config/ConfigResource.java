package com.radcortez.quarkus.config;

import io.quarkus.agroal.runtime.DataSourceJdbcRuntimeConfig;
import io.quarkus.agroal.runtime.DataSourcesJdbcRuntimeConfig;
import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.sql.Connection;

@Path("/config")
public class ConfigResource {
    @Inject
    SmallRyeConfig config;
    @Inject
    DataSource dataSource;
    @Inject
    DataSourcesJdbcRuntimeConfig dataSourcesJdbcRuntimeConfig;

    @GET
    @Path("/{name}")
    public Response get(@PathParam("name") String name) throws Exception {
        final Connection connection = dataSource.getConnection();
        return Response.ok().entity(config.getConfigValue(name)).build();
    }
}
