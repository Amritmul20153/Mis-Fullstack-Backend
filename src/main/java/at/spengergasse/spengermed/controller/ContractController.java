package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contract/")
public class ContractController extends BaseController<Contract> {
    public ContractController(CrudRepository<Contract, String> repository) {
        super(repository);
    }
}

