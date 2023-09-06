package com.radcortez.quarkus.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "config")
public interface Config {
    String value();
}
