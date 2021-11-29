package ar.edu.unq.desapp.grupoH022021.archtests;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects.Log;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoH022021.backenddesappapi")
public class ArchServiceTests {

    @ArchTest
    static final ArchRule services_should_bePublic=
            classes().that().resideInAPackage("..services..")
                    .should().bePublic();

    @ArchTest
    public static final ArchRule services_must_reside_in_a_services_package =
            classes().that().haveNameMatching(".*Service").should().resideInAPackage("..services..")
                    .as("Service should reside in a package '..services..'");

    @ArchTest
    public static ArchRule services_should_be_annotated_with_Service=
            classes()
                    .that().resideInAPackage("..services..")
                    .should().beAnnotatedWith(Service.class);
}
