package at.spengergasse.spengermed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "a_at1plf")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AT1PLF {
    @Id
    String id;
    Integer size;
    String title;
    LocalDateTime creation;

}
