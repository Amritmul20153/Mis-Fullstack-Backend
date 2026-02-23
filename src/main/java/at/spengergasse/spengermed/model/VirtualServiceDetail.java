package at.spengergasse.spengermed.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "v_virtualServiceDetail")
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VirtualServiceDetail extends DomainResource {
}
