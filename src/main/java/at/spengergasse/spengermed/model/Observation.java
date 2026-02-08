package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "ob_identifier", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<>();

    @Column(name = "ob_instantiatesCanonical")
    private String instantiatesCanonical;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_instantiatesReference", referencedColumnName = "id")
    private Reference instantiatesReference;

    @AssertTrue(message = "Either instantiatesCanonical or instantiatesReference must be set, but not both.")
    private boolean isValidInstantiates() {
        return !(instantiatesCanonical != null && instantiatesReference != null);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_basedOn", referencedColumnName = "id")
    private List<Reference> basedOn = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_triggeredBy", referencedColumnName = "id")
    private List<TriggeredBy> triggeredBy = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_partOf", referencedColumnName = "id")
    private List<Reference> partOf = new ArrayList<>();

    public enum Code {
        registered, preliminary, @JsonProperty("final") _final, amended
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "ob_status", nullable = false)
    private Code status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_category", referencedColumnName = "id")
    private List<CodeableConcept> category = new ArrayList<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_code", nullable = false)
    private CodeableConcept code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_subject", referencedColumnName = "id")
    private Reference subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_focus", referencedColumnName = "id")
    private List<Reference> focus = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_encounter", referencedColumnName = "id")
    private Reference encounter;

    @Column(name = "ob_effectiveDateTime")
    private LocalDateTime effectiveDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_effectivePeriod", referencedColumnName = "id")
    private Period effectivePeriod;

    @Column(name = "ob_effectiveTiming")
    private LocalDateTime effectiveTiming;

    @Column(name = "ob_effectiveInstant")
    private LocalDateTime effectiveInstant;

    @Column(name = "ob_issued")
    private LocalDateTime issued;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_performer", referencedColumnName = "id")
    private List<Reference> performer = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valueQuantity", referencedColumnName = "id")
    private Quantity valueQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valueCodeableConcept", referencedColumnName = "id")
    private CodeableConcept valueCodeableConcept;

    @Column(name = "ob_valueString")
    private String valueString;

    @Column(name = "ob_valueBoolean")
    private Boolean valueBoolean;

    @Column(name = "ob_valueInteger")
    private Integer valueInteger;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valueRange", referencedColumnName = "id")
    private Range valueRange;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valueRatio", referencedColumnName = "id")
    private Ratio valueRatio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valueSampledData", referencedColumnName = "id")
    private SampledData valueSampledData;

    @Column(name = "ob_valueTime")
    private String valueTime;

    @Column(name = "ob_valueDateTime")
    private LocalDateTime valueDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_valuePeriod", referencedColumnName = "id")
    private Period valuePeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_dataAbsentReason", referencedColumnName = "id")
    private CodeableConcept dataAbsentReason;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_interpretation", referencedColumnName = "id")
    private List<CodeableConcept> interpretation = new ArrayList<>();

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "ob_note", referencedColumnName = "id")
    //private List<Annotation> note = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_bodySite", referencedColumnName = "id")
    private CodeableConcept bodySite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_bodyStructure", referencedColumnName = "id")
    private Reference bodyStructure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_method", referencedColumnName = "id")
    private CodeableConcept method;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_specimen", referencedColumnName = "id")
    private Reference specimen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_device", referencedColumnName = "id")
    private Reference device;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_referenceRange", referencedColumnName = "id")
    private List<ReferenceRange> referenceRange = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_hasMember", referencedColumnName = "id")
    private List<Reference> hasMember = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_derivedFrom", referencedColumnName = "id")
    private List<Reference> derivedFrom = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_component", referencedColumnName = "id")
    private List<Component> component = new ArrayList<>();

    @AssertTrue(message = "dataAbsentReason must only be present if value[x] is not present.")
    private boolean isValidDataAbsentReason() {
        return dataAbsentReason == null || (valueQuantity == null && valueCodeableConcept == null && valueString == null &&
                valueBoolean == null && valueInteger == null && valueRange == null && valueRatio == null &&
                valueSampledData == null && valueTime == null && valueDateTime == null && valuePeriod == null);
    }

    @AssertTrue(message = "bodyStructure must only be present if bodySite is not present.")
    private boolean isValidBodyStructure() {
        return bodyStructure == null || bodySite == null;
    }
}