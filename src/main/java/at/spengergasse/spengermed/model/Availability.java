package at.spengergasse.spengermed.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "av_availability")
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Builder
public class Availability extends DomainResource{
}
