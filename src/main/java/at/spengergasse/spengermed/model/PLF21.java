package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "p21_plf21")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PLF21 extends DomainResource {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_p21_identifier")
    private List<Identifier> identifier = new ArrayList<>();

    @Column(name = "p21_name")
    private String name;

    @Column (name = "p21_ex")
    private boolean ex;

    @Column (name = "p21_d")
    private LocalDateTime d;

    @OneToMany
    @JoinColumn (name ="p21_me")
    private List<Me>  me = new ArrayList<>();

    @OneToMany
    @JoinColumn (name ="p21_pl")
    private List<Me> pl = new ArrayList<>();


}
