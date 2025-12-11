package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Bundle;
import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.repository.PatientRepository;
import at.spengergasse.spengermed.specification.PatientSpecification;
import at.spengergasse.spengermed.util.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/patient")
public class PatientController extends AbstractSearchController<Patient> {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Bundle search(@RequestParam Map<String, String> params,
                         HttpServletRequest request) {

        // Pagination aus Parametern
        int count = Integer.parseInt(params.getOrDefault("_count", "10"));
        int page = Integer.parseInt(params.getOrDefault("_page", "1")) - 1; // 0-basiert
        Pagination pagination = new Pagination(page, count);

        Specification<Patient> spec = PatientSpecification.buildSpecification(params);
        PageRequest pageable = PageRequest.of(pagination.getPage(), pagination.getCount());

        return searchInternal(
                params,
                request,
                spec,
                repo.findAll(spec, pageable)
        );
    }

    @Override
    protected String getId(Patient entity) {
        return entity.getId();
    }
}
