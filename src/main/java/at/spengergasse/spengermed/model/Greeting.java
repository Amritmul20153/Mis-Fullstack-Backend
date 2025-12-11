package at.spengergasse.spengermed.model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "g_greeting")
@Builder
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private long l;
    private String format;


}
