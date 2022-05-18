package br.com.studies.cadastro.infra.migration;

import io.quarkus.runtime.StartupEvent;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class MigrationService {

    private static final Logger LOGGER = Logger.getLogger(MigrationService.class);

//    @Inject
//    Flyway flyway;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Inicializando MigrationService...");
//        flyway.clean();
//        flyway.migrate();
//        MigrationInfo current = flyway.info().current();
//
//        if (current != null) {
//            LOGGER.info(current.getVersion().getVersion());
//            LOGGER.info(current.getState().toString());
//        } else {
//            LOGGER.info("SEM VERSÃO DE FLYWAY");
//        }
    }
}