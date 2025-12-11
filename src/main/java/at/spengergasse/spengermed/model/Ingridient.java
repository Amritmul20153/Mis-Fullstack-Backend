package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "in_ingridient")
@Builder
public class Ingridient extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_cc_itemCodeableConcept")
    private CodeableConcept itemCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_ref_itemReference")
    private Reference itemReference;

    @Column(name = "p_active")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_ra_strenght")
    private Ratio strength;
}
