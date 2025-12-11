package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "c_contact")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_cc_purpose")
    private CodeableConcept purpose;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_hn_name")
    private HumanName name;
}
