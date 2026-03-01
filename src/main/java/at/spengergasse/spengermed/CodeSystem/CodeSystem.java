package at.spengergasse.spengermed.CodeSystem;


import at.spengergasse.spengermed.model.DomainResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * FHIR: CodeSystem
 * https://www.hl7.org/fhir/codesystem.html
 *
 * Speichert eine vollständige Terminologie als FHIR CodeSystem Ressource.
 * In unserem Fall: Ein Supplement zu SNOMED CT mit ICPC2 und ICD10 Mappings.
 *
 * Metadaten kommen aus dem Excel-Blatt "Hinweise":
 *   url         = "https://termgit.elga.gv.at/CodeSystem/kl-oegam-primarycarecode"
 *   version     = "1.0.0+20230915"
 *   name        = "kl-oegam-primarycarecode"
 *   title       = "KL ÖGAM PrimaryCareCodes"
 *   status      = "active"
 *   content     = "supplement"
 *   supplements = "http://snomed.info/sct"
 *
 * Beziehungen:
 *   1 CodeSystem -> n CodeSystemPropertyDef  (Spalten-Definitionen)
 *   1 CodeSystem -> n CodeSystemConcept      (Zeilen der Excel-Tabelle)
 *   1 CodeSystemConcept -> n ConceptProperty (Werte pro Zeile)
 */
@Entity
@Table(name = "cs_codesystem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CodeSystem extends DomainResource {

    // resourceType ist immer "CodeSystem" - FHIR Standard
    @Column(name = "cs_resourcetype")
    @JsonProperty("resourceType")
    private String resourceType = "CodeSystem";

    // 0..1 url - Canonical identifier als URI (aus Excel Hinweise)
    @Column(name = "cs_url", length = 500)
    @JsonProperty("url")
    private String url;

    // 0..1 version (aus Excel Hinweise)
    @Column(name = "cs_version", length = 50)
    @JsonProperty("version")
    private String version;

    // 0..1 name - maschinenlesbarer Name (aus Excel Hinweise)
    @Column(name = "cs_name", length = 200)
    @JsonProperty("name")
    private String name;

    // 0..1 title - menschenlesbarer Titel (aus Excel Hinweise)
    @Column(name = "cs_title", length = 200)
    @JsonProperty("title")
    private String title;

    // 1..1 status - draft | active | retired | unknown (aus Excel Hinweise)
    @NotNull
    @Column(name = "cs_status", nullable = false, length = 20)
    @JsonProperty("status")
    private String status;

    // 0..1 description - natürlichsprachliche Beschreibung (aus Excel Hinweise)
    @Column(name = "cs_description", columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    // 0..1 copyright (aus Excel Hinweise)
    @Column(name = "cs_copyright", columnDefinition = "TEXT")
    @JsonProperty("copyright")
    private String copyright;

    // 1..1 content - not-present | example | fragment | complete | supplement
    @NotNull
    @Column(name = "cs_content", nullable = false, length = 20)
    @JsonProperty("content")
    private String content;

    // 0..1 supplements - Canonical URL des Basis-CodeSystem (nur wenn content="supplement")
    // In unserem Fall: "http://snomed.info/sct"
    @Column(name = "cs_supplements", length = 500)
    @JsonProperty("supplements")
    private String supplements;

    // 0..* property - Beschreibung der Spalten (Was sind die möglichen Properties?)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "csp_cs_codesystem_id")
    @JsonProperty("property")
    private List<CodeSystemPropertyDef> property = new ArrayList<>();

    // 0..* concept - die eigentlichen Terminologie-Begriffe (Zeilen aus Excel)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "csc_cs_codesystem_id")
    @JsonProperty("concept")
    private List<CodeSystemConcept> concept = new ArrayList<>();
}
