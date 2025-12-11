package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Encounter;
import org.springframework.data.repository.ListCrudRepository;

public interface EncounterRepository extends ListCrudRepository<Encounter, String> {
}