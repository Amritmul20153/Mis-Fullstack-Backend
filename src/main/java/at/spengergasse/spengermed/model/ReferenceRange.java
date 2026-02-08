package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reference_range")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReferenceRange extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_low")
    private SimpleQuantity low; // Low Range (0..1)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_high")
    private SimpleQuantity high; // High Range (0..1)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_normalValue")
    private CodeableConcept normalValue; // Normal value (0..1)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_type")
    private CodeableConcept type; // Reference range qualifier (0..1)

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_appliesTo")
    private List<CodeableConcept> appliesTo = new ArrayList<>(); // Reference range population (0..*)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rr_age")
    private Range age; // Applicable age range (0..1)

    @Column(name = "rr_text")
    private String text; // Text-based reference range (0..1)

    @AssertTrue(message = "At least one of low, high, or text must be present")
    private boolean isValidReferenceRange() {
        return low != null || high != null || (text != null && !text.isEmpty());
    }
}