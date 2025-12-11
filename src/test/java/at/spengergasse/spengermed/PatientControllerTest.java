package at.spengergasse.spengermed;

import at.spengergasse.spengermed.utilities.config.TestSecurityConfig;
import at.spengergasse.spengermed.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
@Import(TestSecurityConfig.class)
@TestPropertySource(locations = "classpath:application-h2.properties")
public class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void testSearchPatients() throws Exception {

        mockMvc.perform(get("/api/patient")
                        .param("family", "Chalmers")
                        .param("_count", "10")
                        .param("_page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("searchset"))
                .andExpect(jsonPath("$.entry[0].resource.id").exists());

        mockMvc.perform(get("/api/patient")
                        .param("gender", "male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entry[0].resource.gender").exists());

        mockMvc.perform(get("/api/patient")
                        .param("birthdate", "1974-12-25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entry[0].resource.birthDate")
                        .value("1974-12-25"));
    }
}
