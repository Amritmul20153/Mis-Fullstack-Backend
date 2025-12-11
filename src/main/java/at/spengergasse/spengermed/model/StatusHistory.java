package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
//import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sth_statusHistory")
public class StatusHistory extends BackboneElement {
    public enum StatusCode {planned, arrived, triaged, @JsonProperty("in-progress") in_progress, onleave, finished, cancelled}

    @Enumerated(EnumType.STRING)
    @Column(name = "sth_status")
    //@NotNull
    private StatusCode status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sth_p_period", nullable = false)
    //@NotNull
    private Period period;
}
