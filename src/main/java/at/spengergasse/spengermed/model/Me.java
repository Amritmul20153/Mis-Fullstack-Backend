package at.spengergasse.spengermed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "me_me")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Me extends DomainResource{

    @OneToOne
    @JoinColumn(name ="me_cc_code")
    private CodeableConcept code;



}
