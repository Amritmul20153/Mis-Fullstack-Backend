package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "qu_qualification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Qualification extends BackboneElement {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_qualification_id")
    private List<Identifier> identifier = new ArrayList<>();

    //darf nicht null sein
    @JoinColumn(name = "qu_cc_codeableConcept")
    @OneToOne(cascade = CascadeType.ALL)
    private CodeableConcept code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_pe_id")
    private Period period;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_ref_id")
    private Reference issuer;


}
