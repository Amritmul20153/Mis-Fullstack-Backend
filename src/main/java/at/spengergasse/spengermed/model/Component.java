package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "com_component")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Component extends BackboneElement {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_code", nullable = false)
    private CodeableConcept code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueQuantity")
    private Quantity valueQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueCodeableConcept")
    private CodeableConcept valueCodeableConcept;

    @Column(name = "o_valueString")
    private String valueString;

    @Column(name = "o_valueBoolean")
    private Boolean valueBoolean;

    @Column(name = "o_valueInteger")
    private Integer valueInteger;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueRange", nullable = true)
    private Range valueRange;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueRatio")
    private Ratio valueRatio;

    @Column(name = "o_valueSampledData")
    private String valueSampledData;

    @Column(name = "o_valueTime")
    private LocalDateTime valueTime;

    @Column(name = "o_valueDateTime")
    private LocalDateTime valueDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valuePeriod")
    private Period valuePeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueAttachment")
    private Attachment valueAttachment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_valueReference")
    private Reference valueReference;

    @AssertTrue(message = "At most one of value[x] fields can be set")
    private boolean isValueChoiceValid() {
        int count = 0;
        if (valueQuantity != null) count++;
        if (valueCodeableConcept != null) count++;
        if (valueString != null) count++;
        if (valueBoolean != null) count++;
        if (valueInteger != null) count++;
        if (valueRange != null) count++;
        if (valueRatio != null) count++;
        if (valueSampledData != null) count++;
        if (valueTime != null) count++;
        if (valueDateTime != null) count++;
        if (valuePeriod != null) count++;
        if (valueAttachment != null) count++;
        if (valueReference != null) count++;
        return count <= 1;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_dataAbsentReason")
    private CodeableConcept dataAbsentReason;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_interpretation")
    private List<CodeableConcept> interpretation = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_referenceRange")
    private List<ReferenceRange> referenceRange = new ArrayList<>();
}