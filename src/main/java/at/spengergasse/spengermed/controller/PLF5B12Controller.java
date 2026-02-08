package at.spengergasse.spengermed.controller;


import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.PLF5B12;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plf5_b12/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PLF5B12Controller extends BaseController<PLF5B12> {
    public PLF5B12Controller(CrudRepository< PLF5B12, String> repository) {
        super(repository);
    }
}
