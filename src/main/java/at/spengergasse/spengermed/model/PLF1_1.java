/*package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
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

    @NotNull
    @Column(name="plf_u")
    private String u;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_plf_id", referencedColumnName = "id")
    private List<Identifier> idi = new ArrayList<>();

    @Column(name = "plf_vs")
    private String vs;

    @Column(name="plf_vdt")
    private LocalDateTime vdt;

    @AssertTrue(message = "Fehler: vs und vdt dürfen nicht gleichzeitig gesetzt sein!")
    public boolean isValid() {
        return !(vs != null && vdt != null);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rt_plf_id", referencedColumnName = "id")
    private List<rTr> rTr=new ArrayList<>();
}




 */





