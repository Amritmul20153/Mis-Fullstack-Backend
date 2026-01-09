package at.spengergasse.spengermed.controller;

import jakarta.validation.Valid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

/**
 * Generischer CRUD-Controller für Subklassen.
 * Kein Spring-Runtime-Fehler mehr.
 */

public abstract class BaseController<T> {

    protected final CrudRepository<T, String> repository;

    // 🔹 Nur protected, damit Subklassen es nutzen
    protected BaseController(CrudRepository<T, String> repository) {
        this.repository = repository;
    }

    // GET ALL
    public Iterable<T> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public ResponseEntity<T> getById(String id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE
    public ResponseEntity<T> create(@Valid T entity) {
        T savedEntity = repository.save(entity);
        return ResponseEntity
                .created(URI.create("/api/" + savedEntity.getClass().getSimpleName().toLowerCase()))
                .body(savedEntity);
    }

    // UPDATE
    public ResponseEntity<T> update(String id, @Valid T updatedEntity) {
        if (repository.existsById(id)) {
            repository.save(updatedEntity);
            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    public ResponseEntity<Void> delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
