package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Intermediate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/intermediate/")
public class IntermediateController extends BaseController<Intermediate> {

    public IntermediateController(CrudRepository<Intermediate, String> repository) {
        super(repository);
    }
}
