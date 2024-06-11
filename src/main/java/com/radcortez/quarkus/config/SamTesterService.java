package com.radcortez.quarkus.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "sam-tester-service", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface SamTesterService {
    String appVersion();

    String appEnvironment();

    String appContext();

    Api api();

    OrdersCollector newOrdersCollector();

    Senders senders();

    interface Api {
        Client client();

        interface Client {
            String subscriptionKey();
        }
    }

    interface OrdersCollector {
        Trigger trigger();

        interface Trigger {
            boolean enabled();

            String everyExpression();
        }
    }

    interface Senders {
        Orange orange();

        interface Orange {
            Integer tCompanyId();

            Integer zCompanyId();

            Integer fanStatusCode();
        }
    }
}
