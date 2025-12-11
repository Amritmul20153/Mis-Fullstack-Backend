package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pay_payload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payload extends BackboneElement {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ep_cc_id", referencedColumnName = "id")
    private List<CodeableConcept> type = new ArrayList<CodeableConcept>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_c_mimetype")
    private List<Coding> mimetype = new ArrayList<Coding>();


}
