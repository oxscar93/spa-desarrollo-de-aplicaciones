package ar.edu.unq.desapp.grupoH022021.archtests;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoH022021.backenddesappapi")
public class ArchRepositoryTests {

    @ArchTest
    static final ArchRule repositories_should_bePublic=
            classes().that().resideInAPackage("..repositories..")
                    .should().bePublic();

    @ArchTest
    public static final ArchRule repository_must_reside_in_a_repositories_package =
            classes().that().haveNameMatching(".*Repository").should().resideInAPackage("..repositories..")
                    .as("Repository should reside in a package '..repositories..'");

    @ArchTest
    public static ArchRule repositories_should_be_extended_with_crudRepository_interface=
            noClasses()
                    .that().resideInAPackage("..repositories..")
                    .should().implement(CrudRepository.class);

    @ArchTest
    public static ArchRule repositories_should_be_annotated_with_Repository=
            classes()
                    .that().resideInAPackage("..repositories..")
                    .should().beAnnotatedWith(Repository.class);
}
