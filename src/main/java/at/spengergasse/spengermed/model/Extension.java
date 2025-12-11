package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ext_extension")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Extension extends Element {
    public enum TypeLCode {@JsonProperty("text/cql") text_cql, @JsonProperty("text/fhirpath") text_fhirpath, @JsonProperty("application/x-fhir-query") application_x_fhirquery}

    @Enumerated(EnumType.STRING)
    @Column(name = "ext_typel")
    private TypeLCode typel;


    @Column(name = "ext_extension_string")
    private String extension;

}
