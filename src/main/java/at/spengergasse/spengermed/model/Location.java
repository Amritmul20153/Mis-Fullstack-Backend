package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private Location.StatusCodeL status;

    @OneToOne
    @JoinColumn(name = "l_operationalStatus")
    private Coding  operationalStatus;

    @Column
    private String name;

    @ElementCollection
    private List<String>alias = new ArrayList<>();

    @Column
    private String description;


    public enum Mode {instance, kind}

    @Enumerated(EnumType.STRING)
    @Column(name = "l_mode")
    private Location.Mode mode;

    @OneToMany
    @JoinColumn(name = "l_type")
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
    private Reference managingOrganization;

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
    @JoinColumn
    private List<Reference> endpoint = new ArrayList<>();
}
