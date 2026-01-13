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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PatientController extends AbstractSearchController<Patient> {


    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    /**
     * FHIR-konforme Suche mit Pagination
     */
    @GetMapping
    public Bundle search(
            @RequestParam Map<String, String> params,
            HttpServletRequest request
    ) {

        // ✅ sichere Pagination (keine Exceptions)
        int count;
        int page;

        try {
            count = Integer.parseInt(params.getOrDefault("_count", "10"));
        } catch (Exception e) {
            count = 10;
        }

        try {
            page = Integer.parseInt(params.getOrDefault("_page", "1")) - 1;
        } catch (Exception e) {
            page = 0;
        }

        Pagination pagination = new Pagination(
                Math.max(page, 0),
                Math.max(count, 1)
        );

        // 🔹 _page & _count nicht an Specification weitergeben
        params.remove("_page");
        params.remove("_count");

        Specification<Patient> spec =
                PatientSpecification.buildSpecification(params);

        PageRequest pageable =
                PageRequest.of(pagination.getPage(), pagination.getCount());

        return searchInternal(
                params,
                request,
                spec,
                repo.findAll(spec, pageable)
        );
    }

    /**
     * Patient anlegen (wichtig für Angular!)
     */
    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return repo.save(patient);
    }

    @Override
    protected String getId(Patient entity) {
        return entity.getId();
    }
}
