package at.spengergasse.spengermed.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "nut_nutrition_product")
public class NutritionProduct extends DomainResource {
    public enum StatusCode {active, inactive, @JsonProperty("entered-in-error") entered_in_error}

    //0..1
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nut_cc_code")
    private CodeableConcept code;

    //0..*
    @Enumerated(EnumType.STRING)
    @Column(name = "nut_status")
    private StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_nut_manufacturer")
    private List<Reference> manufacturer;

    //0..*
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ins_nut_instance")
    private List<Instance> instance;


}
