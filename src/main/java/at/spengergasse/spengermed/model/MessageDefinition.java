package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "md_messagedefinition")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDefinition extends DomainResource {


    @Column(name = "md_url")
    public String url;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_md_id")
    private List<Identifier> identifier = new ArrayList<>();


    @Column(name = "md_version")
    public String version;

    
    @Column(name = "md_versionalgorithmstring")
    public String versionalgorithmstring;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "md_versionalgorithmcoding")
    public Coding versionalgorithmcoding;


}
