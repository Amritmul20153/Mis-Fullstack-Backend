package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, String> {
}
