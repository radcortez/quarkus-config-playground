package com.radcortez.quarkus.config;

import io.quarkus.runtime.configuration.ConfigUtils;
import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigSourceInterceptorFactory;
import io.smallrye.config.ConfigValue;
import io.smallrye.config.EnvConfigSource;
import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.SysPropConfigSource;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;

import static io.smallrye.config.PropertiesConfigSourceProvider.classPathSources;
import static io.smallrye.config.SmallRyeConfigBuilder.META_INF_MICROPROFILE_CONFIG_PROPERTIES;

public class LocalSourcesExcludeInterceptor implements ConfigSourceInterceptorFactory {
    public static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public ConfigSourceInterceptor getInterceptor(final ConfigSourceInterceptorContext context) {
        if (counter.incrementAndGet() == 2 && isBuildTime()) {
            return new ConfigSourceInterceptor() {
                private final SmallRyeConfig config = getConfigForRuntimeRecording();

                @Override
                public ConfigValue getValue(final ConfigSourceInterceptorContext context, final String name) {
                    return config != null ? config.getConfigValue(name) : context.proceed(name);
                }

                @Override
                public Iterator<String> iterateNames(final ConfigSourceInterceptorContext context) {
                    return config != null ? config.getPropertyNames().iterator() : context.iterateNames();
                }
            };
        } else {
            return new ConfigSourceInterceptor() {
                @Override
                public ConfigValue getValue(final ConfigSourceInterceptorContext context, final String name) {
                    return context.proceed(name);
                }
            };
        }
    }

    @Override
    public OptionalInt getPriority() {
        return OptionalInt.of(Integer.MAX_VALUE);
    }

    private static SmallRyeConfig getConfigForRuntimeRecording() {
        SmallRyeConfigBuilder builder = ConfigUtils.emptyConfigBuilder();
        builder.getSources().clear();
        builder.getSourceProviders().clear();
        builder.setAddDefaultSources(false)
               // Read microprofile-config.properties, because we disabled the default sources
               .withSources(classPathSources(META_INF_MICROPROFILE_CONFIG_PROPERTIES, Thread.currentThread().getContextClassLoader()));

        // TODO - Should we reset quarkus.config.location to not record from these sources?
        for (ConfigSource configSource : ConfigProvider.getConfig().getConfigSources()) {
            if (configSource instanceof SysPropConfigSource) {
                continue;
            }
            if (configSource instanceof EnvConfigSource) {
                continue;
            }
            if ("PropertiesConfigSource[source=Build system]".equals(configSource.getName())) {
                continue;
            }
            builder.withSources(configSource);
        }
        return builder.build();
    }

    private static boolean isBuildTime() {
        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        return config.getConfigSource("BuildTime RunTime Fixed").isEmpty();
    }
}
