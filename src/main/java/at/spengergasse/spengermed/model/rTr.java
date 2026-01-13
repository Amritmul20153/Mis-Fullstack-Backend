package at.spengergasse.spengermed.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rt_rTr")
@Builder
public class rTr extends BackboneElement{

    @Column(name = "plf_des")
    private String des;

    public enum supp {create, update, delete}

    @Enumerated(EnumType.STRING)
    @Column(name = "rt_supp")
    private supp supp;



}
