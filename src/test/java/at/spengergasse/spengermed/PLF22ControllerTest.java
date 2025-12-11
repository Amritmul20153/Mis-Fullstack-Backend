package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.PLF22;
import at.spengergasse.spengermed.repository.PLF22Repository;
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
public class PLF22ControllerTest extends AbstractControllerTest {
    @Autowired
    PLF22Repository plf22Repository;
    private static final String TEST_DATA_PATH = "src/test/resources/plf22.json"; // Hier den Pfad anpassen
    private final PLF22 testPLF22 = loadTestData(TEST_DATA_PATH, PLF22.class);
    private final String endpoint = "/api/plf22/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllPLF22s() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAPLF22() throws Exception {
        String id = plf22Repository.save(testPLF22).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAPLF22() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testPLF22);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAPLF22() throws Exception {
        String id = plf22Repository.save(new PLF22()).getId();
        String jsonContent = objectMapper.writeValueAsString(testPLF22);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAPLF22() throws Exception {
        String id = testPLF22.getId();
        performDeleteRequest("/api/plf22 /" + id);
    }
}