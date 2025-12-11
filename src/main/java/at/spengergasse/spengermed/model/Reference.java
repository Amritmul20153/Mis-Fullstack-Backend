package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ref_reference")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reference extends Element {

    @Column(name = "ref_reference")
    private String reference;

    @Column(name = "ref_type")
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_id_identifier")
    private Identifier identifier;

    @Column(name = "ref_display")
    private String display;
}
