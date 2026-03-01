package at.spengergasse.spengermed.CodeSystem;

import at.spengergasse.spengermed.model.DomainResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "cp_conceptproperty")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConceptProperty extends DomainResource {

    // 1..1 - Referenz auf CodeSystem.property.code (z.B. ICPC2ID, SYNONYM, FARBE...)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cp_code", nullable = false)
    @JsonProperty("code")
    private CodeCode code;

    // valueString - für Textwerte (SNOMEDCTBEGRIFFE, ICD10BEGRIFFE, HINWEISE, KRITERIEN, SYNONYM)
    @Column(name = "cp_valuestring", columnDefinition = "TEXT")
    @JsonProperty("valueString")
    private String valueString;

    // valueCode - für kodierte Werte (ICPC2ID, SNOMEDCTID)
    @Column(name = "cp_valuecode", length = 100)
    @JsonProperty("valueCode")
    private String valueCode;

    // valueInteger - für ganzzahlige Werte (FARBE, KOMPONENTE)
    @Column(name = "cp_valueinteger")
    @JsonProperty("valueInteger")
    private Integer valueInteger;
}

