package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "pos_position")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Position extends BackboneElement{

    @Column
    @NotNull
    private String longitude;

    @Column
    @NotNull
    private String latitude;

    @Column
    private String altitude;



}
