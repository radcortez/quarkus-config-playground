package com.radcortez.quarkus.config;

import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilderCustomizer;

public class OptionalFeatureCustomizer implements SmallRyeConfigBuilderCustomizer {
    @Override
    public void configBuilder(SmallRyeConfigBuilder builder) {
        SmallRyeConfig config = new SmallRyeConfigBuilder().addDefaultSources().build();
        if (!config.getValue("optional.mapping.enabled", Boolean.class)) {
            builder.getMappingsBuilder().getMappings().remove(OptionalMapping.class);
        }
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }
}
