package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "o_organitation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Organization extends DomainResource {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_o_organization")
    private List<Identifier> identifier;

    @Column(name = "o_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "add_o_id")
    private List<Address> address = new ArrayList<>();

    @Column(name = "o_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "o_c_contact")
    private Contact contact;


}
