package at.spengergasse.spengermed.CodeSystem;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "csp_codesystemproperty")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeSystemPropertyDef extends DomainResource {

    // 1..1 code - identifiziert diese Property-Definition
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "csp_code", nullable = false)
    @JsonProperty("code")
    private CodeCode code;

    // 0..1 description - optionale Beschreibung der Property
    @Column(name = "csp_description", columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    // 1..1 type - Datentyp: code | string | integer | boolean | dateTime | decimal
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "csp_type", nullable = false)
    @JsonProperty("type")
    private PropertyType type;
}

