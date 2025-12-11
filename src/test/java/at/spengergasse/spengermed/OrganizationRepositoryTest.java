package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Organization;
import at.spengergasse.spengermed.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.assertEntityEquals;
import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/organization.json";
    private Organization testOrganization;

    @BeforeEach
    public void setUp() {
        testOrganization = loadTestData(TEST_DATA_PATH, Organization.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneOrganization() {
        Organization savedOrganization = organizationRepository.save(testOrganization);
        String id = savedOrganization.getId();
        Organization loadedOrganization = organizationRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedOrganization, savedOrganization);
    }

    @Test
    @Transactional
    public void testDeleteOneOrganization() {
        Organization savedOrganization = organizationRepository.save(testOrganization);
        organizationRepository.deleteById(savedOrganization.getId());
        boolean exists = organizationRepository.existsById(savedOrganization.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneOrganization() {
        Organization savedEmptyOrganization = organizationRepository.save(new Organization());
        String savedId = savedEmptyOrganization.getId();
        BeanUtils.copyProperties(testOrganization, savedEmptyOrganization);
        savedEmptyOrganization.setId(savedId);
        organizationRepository.save(savedEmptyOrganization);
        Organization loadedOrganization = organizationRepository.findById(savedEmptyOrganization.getId()).orElseThrow();
        assertEntityEquals(loadedOrganization, testOrganization);
    }

}