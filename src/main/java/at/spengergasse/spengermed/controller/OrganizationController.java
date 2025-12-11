package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization/")
public class OrganizationController extends BaseController<Organization> {

    public OrganizationController(CrudRepository<Organization, String> repository) {
        super(repository);
    }
}
