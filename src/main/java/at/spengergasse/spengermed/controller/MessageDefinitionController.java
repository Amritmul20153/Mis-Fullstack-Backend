package at.spengergasse.spengermed.controller;


import at.spengergasse.spengermed.model.MessageDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/messagedefinition/")
public class MessageDefinitionController extends BaseController<MessageDefinition> {
    public MessageDefinitionController(CrudRepository<MessageDefinition, String> repository) {
        super(repository);
    }
}
