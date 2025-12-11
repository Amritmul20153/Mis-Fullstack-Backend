package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

public interface PatientRepository
        extends JpaRepository<Patient, String>,
        JpaSpecificationExecutor<Patient> {
}