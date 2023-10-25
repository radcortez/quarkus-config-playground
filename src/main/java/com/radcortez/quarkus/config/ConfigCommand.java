package com.radcortez.quarkus.config;

import io.smallrye.config.SmallRyeConfig;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command
public class ConfigCommand implements Runnable {
    @CommandLine.Option(names = {"-n", "--name"}, description = "Configuration name", required = true)
    String name;

    @Inject
    SmallRyeConfig config;

    @Override
    public void run() {
        System.out.println(config.getConfigValue(name));
    }
}
