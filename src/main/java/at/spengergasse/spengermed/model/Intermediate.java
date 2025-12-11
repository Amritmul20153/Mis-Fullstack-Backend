package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "im_intermediate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Intermediate extends DomainResource {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "im_cancellationReason")
    public CodeableConcept cancellationReason;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_im_id")
    private List<Identifier> identifier = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "im_participant")
    private List<Participant> participant = new ArrayList<>();

    @Positive
    @Column(name = "im_recurrenceId")
    public int recurrenceId;


}
