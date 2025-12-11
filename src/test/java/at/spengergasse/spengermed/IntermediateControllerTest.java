package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Intermediate;
import at.spengergasse.spengermed.repository.IntermediateRepository;
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
public class IntermediateControllerTest extends AbstractControllerTest {
    @Autowired
    IntermediateRepository intermediateRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/intermediate.json"; // Hier den Pfad anpassen
    private final Intermediate testIntermediate = loadTestData(TEST_DATA_PATH, Intermediate.class);
    private final String endpoint = "/api/intermediate"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllIntermediates() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAIntermediate() throws Exception {
        String id = intermediateRepository.save(testIntermediate).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAIntermediate() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testIntermediate);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAIntermediate() throws Exception {
        String id = intermediateRepository.save(new Intermediate()).getId();
        String jsonContent = objectMapper.writeValueAsString(testIntermediate);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAIntermediate() throws Exception {
        String id = testIntermediate.getId();
        performDeleteRequest("/api/intermediate/" + id);
    }
}