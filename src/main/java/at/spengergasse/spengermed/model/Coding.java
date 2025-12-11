package at.spengergasse.spengermed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "co_coding")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coding extends Element {

    @Column(name = "co_system")
    private String system;

    @Column(name = "co_version")
    private String version;

    @Column(name = "co_code")
    private String code;

    @Column(name = "co_display")
    private String display;

    @Column(name = "co_userSelected")
    private Boolean userSelected;

}
