package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cc_codeableConcept")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CodeableConcept extends Element {
    @JoinColumn(name = "cc_co_coding")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Coding> coding = new ArrayList<>();

    @Column(name = "cc_text")
    private String text;
}
