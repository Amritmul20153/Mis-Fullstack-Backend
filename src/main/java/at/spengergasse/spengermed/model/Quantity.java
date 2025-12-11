package at.spengergasse.spengermed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "quan_quantity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quantity extends Element {
    public enum ComparatorCode {
        @JsonProperty("<") LESS_THAN,
        @JsonProperty("<=") LESS_THAN_OR_EQUAL,
        @JsonProperty(">=") GREATER_THAN_OR_EQUAL,
        @JsonProperty(">") GREATER_THAN,
        @JsonProperty("ad") ADDITION
    }

    @Column(name = "quan_value")
    private float value;

    @Column(name = "quan_unit")
    private String unit;

    @Pattern(regexp = "\\S*", message = "The URI must not contain whitespace and must be valid.")
    @URL(message = "The URI must be a valid URI format.")
    @Column(name = "quan_system")
    private String system;

    @Column(name = "quan_code")
    private String code;

}
