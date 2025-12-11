package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pr_practitioner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Practitioner extends DomainResource {

    public enum GenderCode {
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_practitioner_id")
    private List<Identifier> identifier = new ArrayList<>();

    @Column(name = "pr_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_pr_id", nullable = true, referencedColumnName = "id")
    private List<HumanName> name = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_pr_id")
    private List<ContactPoint> telecom;

    @Enumerated(EnumType.STRING)
    @Column(name = "pr_gender")
    private Patient.GenderCode gender;

    @Column(name = "pr_birthDate")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "att_pr_id", referencedColumnName = "id")
    private List<Attachment> photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "qu_pr_id", referencedColumnName = "id")
    private List<Qualification> qualification;


}
