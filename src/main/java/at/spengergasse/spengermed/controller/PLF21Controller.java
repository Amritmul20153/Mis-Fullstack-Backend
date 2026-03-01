package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.PLF21;
import at.spengergasse.spengermed.model.PLF5B12;
import org.springframework.data.repository.CrudRepository;

public class PLF21Controller extends BaseController<PLF21> {
    public PLF21Controller(CrudRepository< PLF21, String> repository) {
        super(repository);
    }
}

