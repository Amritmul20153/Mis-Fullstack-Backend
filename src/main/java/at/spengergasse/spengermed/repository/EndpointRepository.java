package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Endpoint;
import org.springframework.data.repository.ListCrudRepository;

public interface EndpointRepository extends ListCrudRepository<Endpoint, String> {
}
