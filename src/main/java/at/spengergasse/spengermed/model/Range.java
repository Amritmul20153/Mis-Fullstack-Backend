package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;


@Entity
@Table(name = "ran_range")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Range extends Element{


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "low")
    private SimpleQuantity low; // Low limit (0..1)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "high")
    private SimpleQuantity high; // High limit (0..1)

    @AssertTrue(message = "If present, low SHALL have a lower value than high")
    private boolean isLowLessThanHigh() {
        if (low != null && high != null) {
            return low.getValue() < high.getValue();
        }
        return true; // Valid if one or both are null
    }
}