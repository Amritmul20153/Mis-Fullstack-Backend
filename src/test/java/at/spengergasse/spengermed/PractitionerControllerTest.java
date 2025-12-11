package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.PractitionerRepository;
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
public class PractitionerControllerTest extends AbstractControllerTest {
    @Autowired
    PractitionerRepository practitionerRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/practitioner.json"; // Hier den Pfad anpassen
    private final Practitioner testPractitioner = loadTestData(TEST_DATA_PATH, Practitioner.class);
    private final String endpoint = "/api/practitioner/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllPractitioners() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAPractitioner() throws Exception {
        String id = practitionerRepository.save(testPractitioner).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAPractitioner() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testPractitioner);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAPractitioner() throws Exception {
        String id = practitionerRepository.save(new Practitioner()).getId();
        String jsonContent = objectMapper.writeValueAsString(testPractitioner);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAPractitioner() throws Exception {
        String id = testPractitioner.getId();
        performDeleteRequest("/api/practitioner/" + id);
    }
}