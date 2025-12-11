package at.spengergasse.spengermed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "em_emergency")
public class Emergency extends BackboneElement {

    @OneToOne
    @JoinColumn(name = "em_hn_fk")
    private HumanName contact;
}
