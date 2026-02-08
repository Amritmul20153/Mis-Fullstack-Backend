package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dr_diagnosticreport")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@Builder
public class DiagnosticReport extends DomainResource{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "1_dr_identifier")
    private List<Identifier> identifier = new ArrayList<>();
}
