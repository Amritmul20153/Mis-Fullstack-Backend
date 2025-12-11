package at.spengergasse.spengermed.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "att_attachment")
@Builder
public class Attachment extends Element {

    public enum ContentTypeCode {
        TEXT_HTML,
        TEXT_PLAIN,
        TEXT_XML,
        APPLICATION_JSON,
        IMAGE_JPEG
    }


    public enum LanguageCode {
        DE, // Deutsch
        EN, // English
        FR, // Français
        ES, // Español
        IT  // Italiano
        // Fügen Sie weitere Sprachcodes hinzu, wie benötigt
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "att_contenttype")
    private ContentTypeCode contentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "att_language")
    private LanguageCode language;

    @Column(name = "att_data", columnDefinition = "LONGTEXT")
    private String data;

    @Column(name = "att_title")
    private String title;

    @Column(name = "att_creation")
    private LocalDateTime creation;

}

