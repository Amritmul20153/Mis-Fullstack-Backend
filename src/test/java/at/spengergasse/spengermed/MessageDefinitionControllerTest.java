package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.MessageDefinition;
import at.spengergasse.spengermed.repository.MessageDefinitionRepository;
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
public class MessageDefinitionControllerTest extends AbstractControllerTest {
    @Autowired
    MessageDefinitionRepository messagedefinitionRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/messagedefinition.json"; // Hier den Pfad anpassen
    private final MessageDefinition testMessageDefinition = loadTestData(TEST_DATA_PATH, MessageDefinition.class);
    private final String endpoint = "/api/messagedefinition/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllMessageDefinitions() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAMessageDefinition() throws Exception {
        String id = messagedefinitionRepository.save(testMessageDefinition).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAMessageDefinition() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testMessageDefinition);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAMessageDefinition() throws Exception {
        String id = messagedefinitionRepository.save(new MessageDefinition()).getId();
        String jsonContent = objectMapper.writeValueAsString(testMessageDefinition);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAMessageDefinition() throws Exception {
        String id = testMessageDefinition.getId();
        performDeleteRequest("/api/messagedefinition/" + id);
    }
}