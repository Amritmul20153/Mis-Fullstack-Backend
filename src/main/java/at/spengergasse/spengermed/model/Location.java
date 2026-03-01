package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "l_location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Location extends DomainResource {


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_l_identifier")
    private List<Identifier> identifier = new ArrayList<>();

    public enum StatusCodeL {active, suspended, inactive}

    @Enumerated(EnumType.STRING)
    @Column(name = "l_status")
    private StatusCodeL status;

    @OneToOne
    @JoinColumn(name = "l_operationalStatus")
    private Coding  operationalStatus;

    @Column(name = "l_name") //hast vergessen Namen bei allen Column
    private String name;

    @ElementCollection
    @CollectionTable(name = "alias_location", joinColumns = @JoinColumn(name = "id")) // das ist richtig -
    private List<String>alias = new ArrayList<>();

    @Column(name = "l_description") //vorsicht nicht vergessen Columnnamen zu schreiben
    private String description;


    public enum Mode {instance, kind}

    @Enumerated(EnumType.STRING)
    @Column(name = "l_mode")
    private Location.Mode mode;

    @OneToMany
    @JoinColumn(name = "l_cc_type") //die klasse mit der ich jz arbeite (kürzel) dann _ die klasse die vorkommt zb. CodeableConcept also cc und dann name von attribut. also zb l_cc_type
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "l_contact")
    private List<ExtendedContactDetail> contact = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "l_address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "l_form")
    private CodeableConcept form;

    @OneToMany
    @JoinColumn (name = "l_position")
    private List<Position> positions =new ArrayList<>(); // wenn klassen wechsel dann so machen - datenytp wird die neue klasse

    @OneToOne
    @JoinColumn (name = "l_managingOrganization")
    private Reference managingOrganization; // wenn reference als datentyp Und in klamma eine klasse dann nur
    // reference nehmen

    @OneToOne
    @JoinColumn (name = "l_partOf")
    private Reference partOf;

    @OneToMany
    @JoinColumn (name ="l_characteristic")
    private List<CodeableConcept> characteristic = new ArrayList<>();

    @OneToMany
    @JoinColumn (name ="l_hoursOfOperation")
    private List<Availability> hoursOfOperation = new ArrayList<>();


    @OneToMany
    @JoinColumn (name ="l_virtualService")
    private List<Availability>  virtualService = new ArrayList<>();

    @OneToMany
    @JoinColumn (name ="l_endpoint")
    private List<Reference> endpoint = new ArrayList<>();
}
