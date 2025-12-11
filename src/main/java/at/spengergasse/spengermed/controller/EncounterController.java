package at.spengergasse.spengermed.controller;


import at.spengergasse.spengermed.model.Encounter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encounter/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EncounterController extends BaseController<Encounter> {
    public EncounterController(CrudRepository<Encounter, String> repository) {
        super(repository);
    }

}
