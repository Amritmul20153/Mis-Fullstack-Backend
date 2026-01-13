package at.spengergasse.spengermed.controller;


import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.PLF1_1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plf1_1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PLF1_1Controller extends BaseController<PLF1_1> {
    public PLF1_1Controller(CrudRepository<PLF1_1, String> repository) {
        super(repository);
    }
}
