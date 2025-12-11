package at.spengergasse.spengermed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// https://hapifhir.io/hapi-fhir/apidocs/hapi-fhir-structures-r4/org/hl7/fhir/r4/model/package-summary.html
@SpringBootApplication
@EntityScan(basePackages = {"at.spengergasse.spengermed.model"})
public class SpengerspitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpengerspitalApplication.class, args);
    }

}
