package at.spengergasse.spengermed.repository;


import at.spengergasse.spengermed.CodeSystem.CodeSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSystemRepository extends CrudRepository<CodeSystem, String> {
}
