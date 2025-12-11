package at.spengergasse.spengermed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "b_batch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Batch extends BackboneElement {
    @Column(name = "b_lotNumber")
    private String lotNumber;

    @Column(name = "b_expirationDate")
    private LocalDateTime expirationDate;
}
