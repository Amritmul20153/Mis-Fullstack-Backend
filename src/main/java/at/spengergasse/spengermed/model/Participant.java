package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "par_paticipant")
public class Participant extends BackboneElement {
    /*
     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "par_cc_type")
     private List<CodeableConcept> type;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "par_pe_id")
     private Period period;

     @OneToOne
     @JoinColumn(name = "par_ref_actor")
     private Reference actor;
 */
    @Column(name = "im_required")
    public boolean required;

    public enum StatusCode {accepted, declined, tentative, @JsonProperty("needs-action") needs_action,}

    @Enumerated(EnumType.STRING)
    @Column(name = "par_status")
    private StatusCode status;


}
