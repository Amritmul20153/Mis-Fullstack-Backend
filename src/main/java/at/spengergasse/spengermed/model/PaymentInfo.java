package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pym_paymentInfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInfo extends BackboneElement{

    public enum Method {cash, transfer, insurance}

    @Enumerated(EnumType.STRING)
    @Column(name = "pym_method")
    private PaymentInfo.Method method;

    @Column(name="pym_dueDate")
    private LocalDateTime date;

}
