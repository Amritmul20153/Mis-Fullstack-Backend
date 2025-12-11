package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ep_endpoint")
@Builder
@Entity
public class Endpoint extends DomainResource {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_ep_id", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    public enum StatusCode {
        active, suspended, error, off, @JsonProperty("entered-in-error") entered_in_error, test
    }


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ep_cc_id", referencedColumnName = "id")
    private List<CodeableConcept> connectionType = new ArrayList<CodeableConcept>();

    @Column(name = "ep_StatusCode")
    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;


    @Column(name = "ep_name")
    private String name;


    @Column(name = "ep_description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ep_cc_id", referencedColumnName = "id")
    private List<CodeableConcept> environmentType = new ArrayList<CodeableConcept>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ep_cp_id", referencedColumnName = "id")
    private List<ContactPoint> contact = new ArrayList<ContactPoint>();


    @OneToOne(cascade = CascadeType.ALL) //OnetoOne wenn es von einer klasse erbt 0zu1
    @JoinColumn(name = "ep_id")
    private Reference period;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ep_pay_id", referencedColumnName = "id")
    private List<Payload> payload = new ArrayList<Payload>();

    @Column(name = "ep_address")
    private String address;

    @Column(name = "ep_header")
    private String header;
}
