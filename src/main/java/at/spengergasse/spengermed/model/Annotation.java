package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "an_annotation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "an_text", nullable = false)
    private String text;

    @Column(name = "an_time")
    private String time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "an_author", referencedColumnName = "id")
    private Reference author;
}