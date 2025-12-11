package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ins_instance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instance extends BackboneElement {

    //0..*
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ins_identifier")
    private List<Identifier> identifier = new ArrayList<>();

    //0..1
    @Column(name = "ins_name")
    private String name;
}
