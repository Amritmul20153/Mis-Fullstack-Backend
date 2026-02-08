package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Bundle;
import at.spengergasse.spengermed.model.Observation;
import at.spengergasse.spengermed.repository.ObservationRepository;
import at.spengergasse.spengermed.specification.ObservationSpecification;
import at.spengergasse.spengermed.util.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/observation")
public class ObservationController extends AbstractSearchController<Observation> {

    private final ObservationRepository repo;

    public ObservationController(ObservationRepository repo) {
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

        Specification<Observation> spec =
                ObservationSpecification.buildSpecification(params);

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
     * Observation anlegen (wichtig für Angular!)
     */
    @PostMapping
    public Observation create(@RequestBody Observation observation) {
        return repo.save(observation);
    }

    @Override
    protected String getId(Observation entity) {
        return entity.getId();
    }
}