package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Endpoint;
import at.spengergasse.spengermed.repository.EndpointRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
@RequestMapping("/api/endepoint/")
public class EndpointController extends BaseController<Endpoint> {

    public EndpointController(EndpointRepository repository) {
        super(repository);
    }

}

