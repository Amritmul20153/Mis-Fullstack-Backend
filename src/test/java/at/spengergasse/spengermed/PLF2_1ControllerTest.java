package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.PLF2_1;
import at.spengergasse.spengermed.repository.PLF2_1Repository;
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
public class PLF2_1ControllerTest extends AbstractControllerTest {
    @Autowired
    PLF2_1Repository plf2_1Repository;
    private static final String TEST_DATA_PATH = "src/test/resources/plf2_1.json"; // Hier den Pfad anpassen
    private final PLF2_1 testPLF2_1 = loadTestData(TEST_DATA_PATH, PLF2_1.class);
    private final String endpoint = "/api/plf2_1"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllPLF2_1s() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAPLF2_1() throws Exception {
        String id = plf2_1Repository.save(testPLF2_1).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAPLF2_1() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testPLF2_1);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAPLF2_1() throws Exception {
        String id = plf2_1Repository.save(new PLF2_1()).getId();
        String jsonContent = objectMapper.writeValueAsString(testPLF2_1);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAPLF2_1() throws Exception {
        String id = testPLF2_1.getId();
        performDeleteRequest("/api/plf2_1/" + id);
    }
}