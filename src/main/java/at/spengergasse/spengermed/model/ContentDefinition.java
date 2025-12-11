package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "co_contentdefinition")
public class ContentDefinition extends Element {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "co_cc_fk", nullable = false)
    private CodeableConcept type;

    @Column(name = "co_publicationdate")
    private LocalDateTime publicationDate;
}
