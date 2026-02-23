package at.spengergasse.spengermed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ex_extendedContactDetail")
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExtendedContactDetail extends DomainResource {
}
