package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ra_ratio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ratio extends Element {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ra_quan_numerator")
    private Quantity numerator;

    //TODO: SimplyQuantity? FHIR --> Money
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ra_denominator")
    private SimpleQuantity denominator;


}
