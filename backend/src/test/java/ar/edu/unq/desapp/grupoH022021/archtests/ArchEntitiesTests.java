package ar.edu.unq.desapp.grupoH022021.archtests;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "ar.edu.unq.desapp.grupoH022021.backenddesappapi")
public class ArchEntitiesTests {

    @ArchTest
    static final ArchRule models_should_bePublic=
            classes().that().resideInAPackage("..model..")
                    .should().bePublic();

    @ArchTest
    static final ArchRule models_should_dto=
            classes().that().resideInAPackage("..dto..")
                    .should().bePublic();

    @ArchTest
    public static final ArchRule model_must_reside_in_a_model_package =
            classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..model..")
                    .as("Model should reside in a package '..model..'");

    @ArchTest
    public static final ArchRule dto_must_reside_in_a_dto_package =
            classes().that().haveNameMatching(".*Dto").should().resideInAPackage("..dto..")
                    .as("Dto should reside in a package '..dto..'");

    @ArchTest
    public static final ArchRule model_should_specify_table_name =
            classes().that().areAnnotatedWith(Entity.class).should()
                    .beAnnotatedWith(Table.class);

    @ArchTest
    public static final ArchRule models_should_specify_an_autoNumeric_field =
            fields().that().areDeclaredInClassesThat()
                           .areAnnotatedWith(Entity.class)
                           .and().areAnnotatedWith(Table.class)
                           .and().haveNameMatching(".*Id")
                           .should().beAnnotatedWith(GeneratedValue.class);

    @ArchTest
    public static final ArchRule models_fields_should_have_annotated_with_column =
            fields().that().areDeclaredInClassesThat()
                    .areAnnotatedWith(Entity.class)
                    .and().areAnnotatedWith(Table.class)
                    .and().haveNameNotMatching(".*Id")
                    .should().beAnnotatedWith(Column.class);



}
