package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enc_encounter")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Encounter extends DomainResource {

    public enum StatusCode {planned, arrived, triaged, @JsonProperty("in-progress") in_progress, onleave, finished, cancelled}

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_enc_identifier")
    private List<Identifier> identifier = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "en_status")
    private StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sth_enc_statusHistory")
    private List<StatusHistory> statusHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_enc_type ")
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Reference subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_enc_subject")
    private List<Reference> episodeOfCare = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "par_enc_paticipient")
    private List<Participant> participant = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_enc_appointment")
    private List<Reference> appointment = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enc_pe_period")
    private Period period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_enc_reasonReference")
    private List<Reference> reasonReference = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dia_enc_diagnosis")
    private List<Diagnosis> diagnosis = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enc__ref_partOf")
    private Reference partOf;
}
