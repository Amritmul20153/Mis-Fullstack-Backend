package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_medication")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Medication extends DomainResource {

    public enum StatusCode {active, inactive, @JsonProperty("entered-in-error") entered_in_error}

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_m_medication")
    private List<Identifier> identifier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m_cc_code")
    private CodeableConcept code;

    @Enumerated(EnumType.STRING)
    @Column(name = "m__status")
    private StatusCode status;

    // NOTE: Different on FHIR Page "marketingAuthorizationHolder"
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m__ref_manufacturer")
    private Reference manufacturer;

    // NOTE: Different on FHIR Page "doseForm"
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m__cc_form")
    private CodeableConcept form;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m_ra_amount")
    private Ratio amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_m_medication")
    private List<Ingridient> ingredient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m_b_batch")
    private Batch batch;


}
