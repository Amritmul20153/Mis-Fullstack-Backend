package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Pattern(regexp = "[A-Za-z0-9\\-\\.]{1,64}")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private String id;
}
