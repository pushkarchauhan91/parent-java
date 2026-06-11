package com.company.rules;

import com.company.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroolsSqlRuleEngine {

    private final KieContainer kieContainer;

    public ValidationResult validate(SqlRuleFact fact) {

        SqlRuleResult result =
                new SqlRuleResult();

        KieSession kieSession = null;

        try {

            kieSession =
                    kieContainer.newKieSession();

            kieSession.setGlobal(
                    "result",
                    result
            );

            kieSession.insert(fact);

            kieSession.fireAllRules();

        } catch (Exception e) {

            log.error(
                    "Drools validation failed",
                    e
            );

            return ValidationResult.failure(
                    "Drools validation failed: "
                            + e.getMessage()
            );

        } finally {

            if (kieSession != null) {
                kieSession.dispose();
            }
        }

        if (!result.isValid()) {

            return ValidationResult.failure(
                    String.join(
                            ", ",
                            result.getErrors()
                    )
            );
        }

        return ValidationResult.success();
    }
}