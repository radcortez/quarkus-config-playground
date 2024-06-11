package com.radcortez.quarkus.config;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SendersConfigTest {
    @Inject
    SendersConfig sendersConfig;
    @Inject
    SamTesterService samTesterService;

    @Test
    void sendersConfig() {
        Assertions.assertEquals(123, sendersConfig.sendersMap().get("test").prop1());
        Assertions.assertEquals(456, sendersConfig.sendersMap().get("test").prop2());
        Assertions.assertEquals(150, sendersConfig.sendersMap().get("test").prop3());
    }
}
