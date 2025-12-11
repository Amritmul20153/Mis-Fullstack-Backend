package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// TODO: check
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "dia_diagnosis")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Diagnosis extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_ref_condition")
    //@NotNull
    private Reference condition;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_cc_use")
    private CodeableConcept use;

    @Column(name = "dia_rank")
    //@PositiveOrZero
    private int rank;

}
