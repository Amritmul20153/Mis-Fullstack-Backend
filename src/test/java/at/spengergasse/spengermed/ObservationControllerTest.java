package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.Observation;
import at.spengergasse.spengermed.repository.ObservationRepository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObservationControllerTest extends AbstractControllerTest {
    @Autowired
    ObservationRepository observationRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/observation.json"; // Hier den Pfad anpassen
    private final Observation testObservation = loadTestData(TEST_DATA_PATH, Observation.class);
    private final String endpoint = "/api/observation"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllObservations() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAObservation() throws Exception {
        String id = observationRepository.save(testObservation).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAObservation() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testObservation);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAObservation() throws Exception {
        String id = observationRepository.save(new Observation()).getId();
        String jsonContent = objectMapper.writeValueAsString(testObservation);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAObservation() throws Exception {
        String id = testObservation.getId();
        System.out.println("hello id: " + id);
        performDeleteRequest("/api/observation/" + id);
    }
}