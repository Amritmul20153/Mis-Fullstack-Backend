package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ObservationRepository extends JpaRepository<Observation, String>, JpaSpecificationExecutor<Observation> {
}