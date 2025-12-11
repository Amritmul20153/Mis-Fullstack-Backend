package at.spengergasse.spengermed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pe_period")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Period extends Element {
    @Column(name = "pe_start")
    public LocalDateTime start;

    @Column(name = "pe_end")
    public LocalDateTime end;
}
