package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "id_identifier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Identifier extends Element {
    public enum IdentifierUseCode {usual, official, temp, secondary, old}

    @Enumerated(EnumType.STRING)
    @Column(name = "id_use")
    private IdentifierUseCode use;

    @Column(name = "id_value")
    private String value;
}
