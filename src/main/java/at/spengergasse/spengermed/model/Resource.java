package at.spengergasse.spengermed.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass //alle Attribute werden in Klassen reingemappt
@Getter
@Setter
@NoArgsConstructor
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Pattern(regexp = "[A-Za-z0-9\\-\\.]{1,64}")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
}
