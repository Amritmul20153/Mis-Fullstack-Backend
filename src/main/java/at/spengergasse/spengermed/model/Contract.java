package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "co_contract")
public class Contract extends DomainResource {

    public enum StatusCode {amended, appended, cancelled, resolved}

    ;
    @OneToMany
    @JoinColumn(name = "i_contract_fk")
    private List<Identifier> identifier = new ArrayList<>();

    @Column(name = "co_version")
    private String version;
    @Column(name = "co_code")
    private StatusCode code;

    @OneToOne
    @JoinColumn(name = "co_contentdefinition_fk")
    private ContentDefinition contentDefinition;
}
