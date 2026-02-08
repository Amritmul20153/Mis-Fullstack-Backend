package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ob_observation")
@Builder
public class Observation extends DomainResource {
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Identifier.class)
    @JoinColumn(name = "i_practitioner_fk", referencedColumnName = "id")
    private List<Identifier> identifier;

    @Column(name = "ob_instantiatesCanonical")
    private String instantiatesCanonical;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_instantiatesReference", referencedColumnName = "id")
    private Reference instantiatesReference;

    @AssertTrue(message = "The value of Observation.instantiatesReference must be set if Observation.instantiatesCanonical is not set")
    private boolean isinstantiatesReferenceorinstantiatesCanonical() {
        if (instantiatesReference != null || instantiatesCanonical != null) {
            return false;
        }
        return true;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_basedOn", referencedColumnName = "id")
    private Reference basedOn;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_triggeredBy", referencedColumnName = "id")
    private List<TriggerdBy> triggeredBy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_partOf", referencedColumnName = "id")
    private List<Reference> partOf = new ArrayList<>();

    public enum Code {
        registered, preliminary, @JsonProperty("final") _final, amended
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "ob_type", nullable = false)
    private Code status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_category", referencedColumnName = "id")
    private List<CodeableConcept> category;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_code", nullable = false)
    private CodeableConcept code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_subject")
    private Reference subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_focus", referencedColumnName = "id")
    private List<Reference> focus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_encounter", referencedColumnName = "id")
    private Reference encounter;

    /*
    @Column(name = "ob_effectiveDateTime")
    private LocalDateTime effectiveDateTime;

    @Column(name = "ob_effectiveDateTime")
    @JoinColumn(name = "ob_effectivePeriod", insertable = false, updatable = false)
    private Period effectivePeriod;
    private LocalDateTime effectiveTiming;
    private LocalDateTime effectiveInstant;

*/
    @Column(name = "ob_effectiveDateTime")
    private LocalDateTime effectiveDateTime;

    @Column(name = "ob_effectiveDateTime", insertable = false, updatable = false)
    private Period effectivePeriod;

    private LocalDateTime effectiveTiming;
    private LocalDateTime effectiveInstant;


}
