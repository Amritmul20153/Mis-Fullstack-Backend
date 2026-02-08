package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.PLF5B12;
import at.spengergasse.spengermed.repository.PLF5B12Repository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PLF5B12ControllerTest extends AbstractControllerTest {

    @Autowired
    PLF5B12Repository plf5_b12Repository;
    private static final String TEST_DATA_PATH = "src/test/resources/plf5_b12.json"; // Hier den Pfad anpassen
    private final PLF5B12 testPLF5B12 = loadTestData(TEST_DATA_PATH, PLF5B12.class);
    private final String endpoint = "/api/plf5_b12/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllPLF5B12s() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAPLF5B12() throws Exception {
        String id = plf5_b12Repository.save(testPLF5B12).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAPLF5B12() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testPLF5B12);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAPLF5B12() throws Exception {
        String id = plf5_b12Repository.save(new PLF5B12()).getId();
        String jsonContent = objectMapper.writeValueAsString(testPLF5B12);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAPLF5B12() throws Exception {
        String id = testPLF5B12.getId();
        System.out.println("hello id: " + id);
        performDeleteRequest("/api/plf5_b12/" + id);
    }
}