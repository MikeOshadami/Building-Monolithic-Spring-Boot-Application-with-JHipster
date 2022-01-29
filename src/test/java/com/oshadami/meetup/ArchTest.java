package com.oshadami.meetup;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.oshadami.meetup");

        noClasses()
            .that()
            .resideInAnyPackage("com.oshadami.meetup.service..")
            .or()
            .resideInAnyPackage("com.oshadami.meetup.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.oshadami.meetup.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
