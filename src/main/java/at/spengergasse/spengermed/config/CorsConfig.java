package at.spengergasse.spengermed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Erlaube spezifische Ursprünge
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Erlaube spezifische Header
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));

        // Erlaube spezifische Methoden
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Erlaube Credentials
        config.setAllowCredentials(true);

        // Konfiguration für alle Pfade anwenden
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}