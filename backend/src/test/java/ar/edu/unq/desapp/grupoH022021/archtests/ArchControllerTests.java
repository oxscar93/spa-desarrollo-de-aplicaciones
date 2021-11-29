package ar.edu.unq.desapp.grupoH022021.archtests;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects.Log;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoH022021.backenddesappapi")
public class ArchControllerTests {

    @ArchTest
    static final ArchRule controllers_should_bePublic=
            classes().that().resideInAPackage("..webservices..")
                    .should().bePublic();

    @ArchTest
    static final ArchRule controllers_should_beAnnotatedWithRestController=
            classes().that().resideInAPackage("..webservices..")
                    .should().beAnnotatedWith(RestController.class);

    @ArchTest
    public static final ArchRule controllers_must_reside_in_a_webservices_package =
            classes().that().haveNameMatching(".*Controller").should().resideInAPackage("..webservices..")
                    .as("Controller should reside in a package '..webservices..'");

    @ArchTest
    public static ArchRule controllers_should_has_only_private_fields =
            fields()
                    .that().areDeclaredInClassesThat().resideInAPackage("..webservices..")
                    .should().bePrivate();

    @ArchTest
    public static ArchRule public_methods_should_has_log_annotation =
            methods()
                    .that().areDeclaredInClassesThat().resideInAPackage("..webservices..")
                    .and().arePublic()
                    .should().beAnnotatedWith(Log.class);
}
