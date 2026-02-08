package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plf2_1")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PLF2_1 extends DomainResource {

    public enum StatusCode {active, draft, @JsonProperty("entered-in-error") entered_in_error, rejected}

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "plf2_status")
    private StatusCode status;

    @ElementCollection
    @Column(name = "plf2_date")
    private List<LocalDate> date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plf2_reg_regard")
    private List<Regard> regard = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rt_plf_id", referencedColumnName = "id")
    private List<rTr> rTr=new ArrayList<>();



}
