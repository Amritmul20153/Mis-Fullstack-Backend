package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "a_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address extends Element {

    public enum UseCode {home, work, temp, old, billing}

    public enum UseType {postal, physical, both}

    @Enumerated(EnumType.STRING)
    @Column(name = "a_use")
    private UseCode use;

    @Enumerated(EnumType.STRING)
    @Column(name = "a_type")
    private UseType type;

    @Column(name = "a_text")
    private String text;

    @ElementCollection
    @CollectionTable(name = "a_address_line")
    private List<String> line = new ArrayList<>();

    @Column(name = "a_city")
    private String city;

    @Column(name = "a_district")
    private String district;

    @Column(name = "a_state")
    private String state;

    @Column(name = "a_postalcode")
    private String postalCode;

    @Column(name = "a_country")
    private String country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_pe_id")
    private Period period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_pr_id", referencedColumnName = "id")
    private List<Address> address;


}
