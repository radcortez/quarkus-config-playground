package com.radcortez.quarkus.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithParentName;

import java.util.Map;

@ConfigMapping(prefix="my-service.senders")
public interface SendersConfig {
    @WithParentName
    Map<String, SenderConfig> sendersMap();
}