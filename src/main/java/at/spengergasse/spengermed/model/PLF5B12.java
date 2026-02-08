package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "plf5_b12")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PLF5B12 extends Resource{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_plf5_id", referencedColumnName = "id")
    private List<Identifier> billId = new ArrayList<>();


    public enum Phase {draft, issued,balanced, @JsonProperty("can-celled") can_celled}

    @Enumerated(EnumType.STRING)
    @Column(name = "plf5_phase")
    private PLF5B12.Phase phase;

    // 0..1 -> OneToOne
// andere Klasse als Attribut
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "plf5_issuerOrg_id")
    private Reference issuerOrg;



    @Column(name = "plf5_totalNet")
    private int totalNet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pym_plf5_id", referencedColumnName = "id")
    private List<rTr> pym=new ArrayList<>();




}
