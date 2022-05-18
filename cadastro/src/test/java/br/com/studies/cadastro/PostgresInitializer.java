package br.com.studies.cadastro;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class PostgresInitializer implements QuarkusTestResourceLifecycleManager {

    public PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres")
            .withUsername("cadastro")
            .withPassword("cadastro")
            .withDatabaseName("ifood_cadastro");

    @Override
    public Map<String, String> start() {
        postgres.start();

        return Map.of("quarkus.datasource.jdbc.driver", postgres.getDriverClassName(),
                "quarkus.datasource.jdbc.url", postgres.getJdbcUrl());
    }

    @Override
    public void stop() {
        if (postgres != null && postgres.isRunning()) {
            postgres.stop();
        }
    }
}
