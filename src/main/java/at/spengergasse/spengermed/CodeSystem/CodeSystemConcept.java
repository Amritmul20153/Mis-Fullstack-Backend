package at.spengergasse.spengermed.CodeSystem;

import at.spengergasse.spengermed.model.DomainResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "csc_codesystemconcept")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeSystemConcept extends DomainResource {

    // 1..1 code - eindeutiger Code des Concepts (= SNOMED CT ID, Spalte "id" in Excel)
    @NotNull
    @Column(name = "csc_code", nullable = false, length = 50)
    @JsonProperty("code")
    private String code;

    // 0..1 display - Anzeigename (= Spalte "Begriffe" in Excel)
    @Column(name = "csc_display", length = 500)
    @JsonProperty("display")
    private String display;

    // 0..* property - alle Property-Werte dieses Concepts
    // (ICPC2ID, SNOMEDCTID, SNOMEDCTBEGRIFFE, ICD10BEGRIFFE, FARBE, KOMPONENTE, KRITERIEN, HINWEISE, SYNONYM)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_csc_concept_id")
    @JsonProperty("property")
    private List<ConceptProperty> property = new ArrayList<>();
}