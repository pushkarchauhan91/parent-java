package com.company.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {

        KieServices kieServices =
                KieServices.Factory.get();

        KieFileSystem kieFileSystem =
                kieServices.newKieFileSystem();

        kieFileSystem.write(
                "src/main/resources/rules/sql-validation.drl",
                kieServices.getResources()
                        .newClassPathResource(
                                "rules/sql-validation.drl"
                        )
        );

        KieBuilder kieBuilder =
                kieServices.newKieBuilder(kieFileSystem);

        kieBuilder.buildAll();

        if (
                kieBuilder.getResults()
                        .hasMessages(Message.Level.ERROR)
        ) {
            throw new IllegalStateException(
                    kieBuilder.getResults().toString()
            );
        }

        return kieServices.newKieContainer(
                kieServices.getRepository()
                        .getDefaultReleaseId()
        );
    }
}