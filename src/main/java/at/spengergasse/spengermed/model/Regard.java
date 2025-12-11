package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reg_regard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Regard extends BackboneElement {
    public enum TypeCode {deny, permit}


    @Enumerated(EnumType.STRING)
    @Column(name = "plf2_type")
    private TypeCode type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reg_dat_data")
    private List<Data> data = new ArrayList<>();


}
