package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.MessageDefinition;
import org.springframework.data.repository.CrudRepository;


public interface MessageDefinitionRepository extends CrudRepository<MessageDefinition, String> {
}
