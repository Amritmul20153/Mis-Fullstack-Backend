package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medication/")
public class MedicationController extends BaseController<Medication> {
    public MedicationController(CrudRepository<Medication, String> repository) {
        super(repository);
    }
}
