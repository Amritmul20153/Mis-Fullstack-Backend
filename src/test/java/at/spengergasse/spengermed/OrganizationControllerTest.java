package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Organization;
import at.spengergasse.spengermed.repository.OrganizationRepository;
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
public class OrganizationControllerTest extends AbstractControllerTest {
    @Autowired
    OrganizationRepository organizationRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/organization.json"; // Hier den Pfad anpassen
    private final Organization testOrganization = loadTestData(TEST_DATA_PATH, Organization.class);
    private final String endpoint = "/api/organization/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllOrganizations() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAOrganization() throws Exception {
        String id = organizationRepository.save(testOrganization).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAOrganization() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testOrganization);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAOrganization() throws Exception {
        String id = organizationRepository.save(new Organization()).getId();
        String jsonContent = objectMapper.writeValueAsString(testOrganization);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAOrganization() throws Exception {
        String id = testOrganization.getId();
        performDeleteRequest("/api/organization/" + id);
    }
}