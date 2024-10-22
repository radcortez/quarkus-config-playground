package com.radcortez.quarkus.config;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Unremovable
@ApplicationScoped
public class LogsSummarizer {
    @ConfigProperty(name = "aaa.logsummary.period")
    String secondsPreiod;
}