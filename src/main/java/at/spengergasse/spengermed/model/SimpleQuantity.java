package at.spengergasse.spengermed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

//TODO: Ich habe das ans Json angepasst. Ressource in FHIR nicht gefunden
@Entity
@Table(name = "squan_simpleQuantity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleQuantity extends Element {
    @Column(name = "squan_value")
    private float value;

    @Pattern(regexp = "\\S*", message = "The URI must not contain whitespace and must be valid.")
    @URL(message = "The URI must be a valid URI format.")
    @Column(name = "squan_system")
    private String system;

    @Column(name = "squan_code")
    private String code;

}
