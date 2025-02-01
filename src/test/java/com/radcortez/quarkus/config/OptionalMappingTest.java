package com.radcortez.quarkus.config;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OptionalMappingTest {
    @Inject
    SmallRyeConfig config;

    @Test
    void optionalMapping() {

    }
}
