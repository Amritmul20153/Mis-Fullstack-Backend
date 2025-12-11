package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.repository.MedicationRepository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicationControllerTest extends AbstractControllerTest {
    @Autowired
    MedicationRepository medicationRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/medication.json"; // Hier den Pfad anpassen
    private final Medication testMedication = loadTestData(TEST_DATA_PATH, Medication.class);
    private final String endpoint = "/api/medication/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllMedications() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAMedication() throws Exception {
        String id = medicationRepository.save(testMedication).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAMedication() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testMedication);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAMedication() throws Exception {
        String id = medicationRepository.save(new Medication()).getId();
        String jsonContent = objectMapper.writeValueAsString(testMedication);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAMedication() throws Exception {
        String id = testMedication.getId();
        performDeleteRequest("/api/medication/" + id);
    }
}