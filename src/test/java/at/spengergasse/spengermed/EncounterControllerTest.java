package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.repository.EncounterRepository;
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
public class EncounterControllerTest extends AbstractControllerTest {
    @Autowired
    EncounterRepository encounterRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/encounter.json"; // Hier den Pfad anpassen
    private final Encounter testEncounter = loadTestData(TEST_DATA_PATH, Encounter.class);
    private final String endpoint = "/api/encounter"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllEncounters() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAEncounter() throws Exception {
        String id = encounterRepository.save(testEncounter).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAEncounter() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testEncounter);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAEncounter() throws Exception {
        String id = encounterRepository.save(new Encounter()).getId();
        String jsonContent = objectMapper.writeValueAsString(testEncounter);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAEncounter() throws Exception {
        String id = testEncounter.getId();
        System.out.println("hello id: " + id);
        performDeleteRequest("/api/encounter/" + id);
    }
}