package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hn_humanname")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HumanName extends Element {
    public enum NameUseCode {usual, official, temp, nickname, anonymous, old, maiden}

    @Enumerated(EnumType.STRING)
    @Column(name = "hn_use")
    private NameUseCode nameUse;

    @Column(name = "hn_text")
    private String text;

    @Column(name = "hn_family")
    private String family;

    @ElementCollection
    @CollectionTable(name = "given_humanname", joinColumns = @JoinColumn(name = "id"))
    private List<String> given = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "prefix_humanname", joinColumns = @JoinColumn(name = "id"))
    private List<String> prefix = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "suffix_humanname", joinColumns = @JoinColumn(name = "id"))
    private List<String> suffix = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_pe_id")
    private Period period;


}
