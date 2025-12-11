package at.spengergasse.spengermed.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class DomainResource extends Resource {
    @OneToOne(cascade = CascadeType.ALL)
    //Wenn die Domainresource gelöscht wird, soll die FKs ebenfalls gelösche werden.
    @JoinColumn(name = "dr_narrative_id")
    private Narrative text;
}