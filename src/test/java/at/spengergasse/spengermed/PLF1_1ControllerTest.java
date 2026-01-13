package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.PLF1_1;
import at.spengergasse.spengermed.repository.EncounterRepository;
import at.spengergasse.spengermed.repository.PLF1_1Repository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PLF1_1ControllerTest extends AbstractControllerTest {
    @Autowired
    PLF1_1Repository plf1_1Repository;
    private static final String TEST_DATA_PATH = "src/test/resources/plf1_1.json"; // Hier den Pfad anpassen
    private final PLF1_1 testPLF1_1 = loadTestData(TEST_DATA_PATH, PLF1_1.class);
    private final String endpoint = "/api/plf1_1"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllPLF1_1s() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAPLF1_1() throws Exception {
        String id = plf1_1Repository.save(testPLF1_1).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAPLF1_1() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testPLF1_1);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAPLF1_1() throws Exception {
        String id = plf1_1Repository.save(new PLF1_1()).getId();
        String jsonContent = objectMapper.writeValueAsString(testPLF1_1);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAPLF1_1() throws Exception {
        String id = testPLF1_1.getId();
        System.out.println("hello id: " + id);
        performDeleteRequest("/api/plf1_1/" + id);
    }
}