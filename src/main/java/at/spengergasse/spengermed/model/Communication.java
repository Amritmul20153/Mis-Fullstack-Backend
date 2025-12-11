package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "com_coding")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Communication extends BackboneElement {
    //darf nicht null sein
    @JoinColumn(name = "com_cc_language_id")
    @OneToOne(cascade = CascadeType.ALL)
    private CodeableConcept language;

    @Column(name = "com_preffered")
    private Boolean preferred;
}
