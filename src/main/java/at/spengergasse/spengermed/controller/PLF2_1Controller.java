package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.PLF2_1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plf2_1/")
public class PLF2_1Controller extends BaseController<PLF2_1> {
    public PLF2_1Controller(CrudRepository<PLF2_1, String> repository) {
        super(repository);
    }
}
