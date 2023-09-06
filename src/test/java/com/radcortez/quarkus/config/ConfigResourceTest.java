package com.radcortez.quarkus.config;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ConfigResourceTest {
    @Inject
    SmallRyeConfig config;

    @Test
    void config() {
        System.out.println(config.getConfigValue("config.value"));
    }
}
