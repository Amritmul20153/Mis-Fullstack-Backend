package at.spengergasse.spengermed;

import at.spengergasse.spengermed.CodeSystem.CodeSystem;
import at.spengergasse.spengermed.repository.CodeSystemRepository;
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
public class CodeSystemControllerTest extends AbstractControllerTest {

    @Autowired
    CodeSystemRepository codeSystemRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/codesystem.json";
    private final CodeSystem testCodeSystem = loadTestData(TEST_DATA_PATH, CodeSystem.class);
    private final String endpoint = "/api/codesystem";

    @Test
    public void testGetAllCodeSystems() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetACodeSystem() throws Exception {
        String id = codeSystemRepository.save(testCodeSystem).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostACodeSystem() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testCodeSystem);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutACodeSystem() throws Exception {
        String id = codeSystemRepository.save(new CodeSystem()).getId();
        String jsonContent = objectMapper.writeValueAsString(testCodeSystem);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteACodeSystem() throws Exception {
        String id = testCodeSystem.getId();
        performDeleteRequest(endpoint + "/" + id);
    }
}
