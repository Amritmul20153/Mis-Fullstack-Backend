package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plf1_1")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PLF1_1 extends DomainResource{

    @Column(name = "plf1_u")
    private String u;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_plf1_fk")
    private List<Identifier> identifier;

    @Column(name = "plf1_vs")
    private String vs;

    @Column(name = "plf1_vdt")
    private String vdt;











}
