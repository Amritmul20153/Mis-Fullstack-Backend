package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.PractitionerRepository;
import at.spengergasse.spengermed.model.Bundle;
import at.spengergasse.spengermed.specification.PractitionerSpecification;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/practitioner")
public class PractitionerController extends AbstractSearchController<Practitioner> {

    private final PractitionerRepository repo;

    public PractitionerController(PractitionerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Bundle search(@RequestParam Map<String, String> params,
                         HttpServletRequest request) {

        int count = Integer.parseInt(params.getOrDefault("_count", "10"));
        int page = Integer.parseInt(params.getOrDefault("_page", "1")) - 1;

        var spec = PractitionerSpecification.build(params);
        var pageable = PageRequest.of(page, count);

        return searchInternal(
                params,
                request,
                spec,
                repo.findAll(spec, pageable)
        );
    }

    @Override
    protected String getId(Practitioner entity) {
        return entity.getId();
    }
}
