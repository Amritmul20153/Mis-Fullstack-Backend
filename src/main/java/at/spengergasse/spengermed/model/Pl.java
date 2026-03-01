package at.spengergasse.spengermed.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "pl_pl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pl  extends BackboneElement{

    @OneToOne
    @JoinColumn(name = "pl_reference")
    private Reference reference;
}
