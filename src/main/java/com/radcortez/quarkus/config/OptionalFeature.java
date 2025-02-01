package com.radcortez.quarkus.config;

import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@LookupIfProperty(name = "optional.mapping.enabled", stringValue = "true")
public class OptionalFeature {
    @Inject
    OptionalMapping optionalMapping;
}
