package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.CodeSystem.CodeSystem;
import at.spengergasse.spengermed.repository.CodeSystemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codesystem")
@CrossOrigin(origins = "http://localhost:4200")
public class CodeSystemController extends BaseController<CodeSystem> {

    public CodeSystemController(CodeSystemRepository repository) {
        super(repository);
    }

    @GetMapping
    public Iterable<CodeSystem> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeSystem> getById(@PathVariable String id) {
        return super.getById(id);
    }

    @PostMapping
    public ResponseEntity<CodeSystem> create(@RequestBody CodeSystem codeSystem) {
        return super.create(codeSystem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodeSystem> update(@PathVariable String id,
                                             @RequestBody CodeSystem codeSystem) {
        return super.update(id, codeSystem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return super.delete(id);
    }
}

