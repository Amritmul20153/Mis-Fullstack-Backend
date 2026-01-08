package at.spengergasse.spengermed.controller;

import jakarta.validation.Valid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public abstract class BaseController<T> {
    private final CrudRepository<T, String> repository;


    public BaseController(CrudRepository<T, String> repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<T> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<T> getById(@PathVariable String id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T entity) {
        T savedEntity = repository.save(entity);
        return ResponseEntity
                .created(URI.create("/api/" + entity.getClass().getSimpleName().toLowerCase() + "/" + savedEntity.toString()))
                .body(savedEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable String id, @Valid @RequestBody T updatedEntity) {
        return repository.findById(id)
                .map(entity -> {
                    repository.save(updatedEntity);
                    return ResponseEntity.ok(updatedEntity);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}