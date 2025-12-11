package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pl_plf22")
public class PLF22 extends Resource {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_patient_fk")
    private List<Identifier> identifier = new ArrayList<>();


    @Column(name = "pl_active")
    private Boolean active;

    @Column(name = "pl_description")
    private String description;

    //Cascade nicht vergessen
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "em_contact_fk")
    private List<Emergency> emergency = new ArrayList<>();
}
