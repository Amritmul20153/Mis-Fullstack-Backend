package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "sd_sampleddata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampledData extends Element {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sd_origin_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private SimpleQuantity origin; // Zero value and units

    @Column(name = "sd_interval")
    private Double interval; // Number of intervalUnits between samples

    @Column(name = "sd_interval_unit", nullable = false)
    @NotNull
    private String intervalUnit; // The measurement unit of the interval between samples

    @Column(name = "sd_factor")
    private Double factor; // Multiply data by this before adding to origin

    @Column(name = "sd_lower_limit")
    private Double lowerLimit; // Lower limit of detection

    @Column(name = "sd_upper_limit")
    private Double upperLimit; // Upper limit of detection

    @Column(name = "sd_dimensions", nullable = false)
    @NotNull
    @Positive
    private Integer dimensions; // Number of sample points at each time point

    @Column(name = "sd_code_map")
    private String codeMap; // Defines the codes used in the data

    @Column(name = "sd_offsets")
    private String offsets; // Offsets, typically in time, at which data values were taken

    @Column(name = "sd_data")
    private String data; // Decimal values with spaces, or "E" | "U" | "L", or another code

    @AssertTrue(message = "A SampledData SHALL have either an interval or offsets, but not both.")
    private boolean isValidIntervalAndOffsets() {
        return !(interval != null && offsets != null);
    }
}