package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tr_triggeredBy")
@Builder
public class TriggeredBy extends BackboneElement {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tr_re_id", referencedColumnName = "id", nullable = false)
    private Reference observation;

    public enum Code {reflex, repeat, @JsonProperty("re-run") re_run}

    //Enum steht für eine Aufzählung von Konstanten
    @Enumerated(EnumType.STRING)
    //Ordinal und string der unterschied ist das ordinal die position im enum speichert und string den namen
    @NotNull
    @Column(name = "tr_type", nullable = false)
    private Code type;

    @Column(name = "tr_reason", length = 1000)
    private String reason;
}


