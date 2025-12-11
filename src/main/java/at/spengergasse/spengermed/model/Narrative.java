package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "na_narrative")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder //Pattern, Objekt wird kein Konstruktor, sondern Builder (habs nicht verstanden)
public class Narrative extends Element {
    public enum NarrativeStatusCode {generated, extentions, additional, empty} //enum für "code" --> Auswahl mit den Optionen in {}


    @Enumerated(EnumType.STRING)
    @Column(name = "na_status")
    @NotNull
    private NarrativeStatusCode status;

    @Column(name = "na_div", columnDefinition = "LONGTEXT")
    @NotNull
    @Lob
    private String div;

}
