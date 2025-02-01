package com.radcortez.quarkus.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "optional.mapping")
public interface OptionalMapping {
    boolean enabled();

    String value();
}
