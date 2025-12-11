package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;

public interface PractitionerRepository
        extends JpaRepository<Practitioner, String>,
        JpaSpecificationExecutor<Practitioner> {
}
