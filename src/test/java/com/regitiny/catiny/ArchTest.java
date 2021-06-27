package com.regitiny.catiny;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.regitiny.catiny.GeneratedByJHipster;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.regitiny.catiny");

        noClasses()
            .that()
            .resideInAnyPackage("com.regitiny.catiny.service..")
            .or()
            .resideInAnyPackage("com.regitiny.catiny.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.regitiny.catiny.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
