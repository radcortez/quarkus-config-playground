package com.radcortez.quarkus.config;

import io.quarkus.agroal.runtime.DataSourceJdbcRuntimeConfig;
import io.quarkus.agroal.runtime.DataSourcesJdbcRuntimeConfig;
import io.quarkus.datasource.runtime.DataSourceBuildTimeConfig;
import io.quarkus.datasource.runtime.DataSourcesBuildTimeConfig;
import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@Path("/config")
public class ConfigResource {
    @Inject
    SmallRyeConfig config;
    @Inject
    DataSource dataSource;
    @Inject
    DataSourcesBuildTimeConfig dataSourcesBuildTimeConfig;

    @GET
    @Path("/{name}")
    public Response get(@PathParam("name") String name) throws Exception {
        DataSourceBuildTimeConfig dataSourceBuildTimeConfig = dataSourcesBuildTimeConfig.defaultDataSource;
        Map<String, String> volumes = dataSourceBuildTimeConfig.devservices.volumes;
        for (Map.Entry<String, String> entry : volumes.entrySet()) {
            System.out.println(entry);
        }
        return Response.ok().entity(config.getConfigValue(name)).build();
    }
}
