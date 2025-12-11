package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Contract;
import at.spengergasse.spengermed.repository.ContractRepository;
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
public class ContractControllerTest extends AbstractControllerTest {
    
    @Autowired
    ContractRepository contractRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/contract.json"; // Hier den Pfad anpassen
    private final Contract testContract = loadTestData(TEST_DATA_PATH, Contract.class);
    private final String endpoint = "/api/contract/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllContracts() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAContract() throws Exception {
        String id = contractRepository.save(testContract).getId();
        System.out.println(id);
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAContract() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testContract);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAContract() throws Exception {
        String id = contractRepository.save(new Contract()).getId();
        String jsonContent = objectMapper.writeValueAsString(testContract);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAContract() throws Exception {
        String id = testContract.getId();
        performDeleteRequest("/api/contract/" + id);
    }
}