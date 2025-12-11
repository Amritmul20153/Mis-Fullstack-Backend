package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Organization;
import org.springframework.data.repository.ListCrudRepository;

public interface OrganizationRepository extends ListCrudRepository<Organization, String> {
}
