package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dat_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Data extends BackboneElement {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dat_extension")
    private Extension extension;
}
