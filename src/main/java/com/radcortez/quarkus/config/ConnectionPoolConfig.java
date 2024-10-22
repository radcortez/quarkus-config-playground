package com.radcortez.quarkus.config;

import io.quarkus.arc.Unremovable;
import io.smallrye.config.ConfigMapping;

@Unremovable
@ConfigMapping(prefix = "aaa.ldap.pool")
public interface ConnectionPoolConfig {
    String value();
}