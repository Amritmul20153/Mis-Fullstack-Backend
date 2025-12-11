package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "p_patient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient extends DomainResource {

    public enum GenderCode {
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_patient_id")
    private List<Identifier> identifier = new ArrayList<>();


    @Column(name = "p_active")
    private Boolean active;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_p_id", nullable = true, referencedColumnName = "id")
    private List<HumanName> name = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_p_id")
    private List<ContactPoint> telecom;

    @Enumerated(EnumType.STRING)
    @Column(name = "p_gender")
    private GenderCode gender;

    @Column(name = "p_birthDate")
    private LocalDate birthDate;


    @Column(name = "p_deceasedboolean")
    private Boolean deceasedBoolean;

    @Column(name = "p_deceaseddatetime")
    private LocalDateTime deceasedDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_p_id", referencedColumnName = "id")
    private List<Address> address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "att_p_id", referencedColumnName = "id")
    private List<Attachment> photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_p_id")
    private List<Reference> generalPractitioner;

   /* @PrePersist
    protected void onCreate() {
        // Prüfe, ob nur eine der beiden Spalten befüllt ist
        if ((deceasedBoolean == null && deceasedDateTime == null) ||
                (deceasedBoolean != null && deceasedDateTime != null)) {
            throw new IllegalStateException("Entweder 'deceasedBoolean' oder 'deceasedDateTime' sollte befüllt sein.");
        }
    }
*/
}
