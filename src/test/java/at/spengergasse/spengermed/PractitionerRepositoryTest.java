package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Practitioner;
import at.spengergasse.spengermed.repository.PractitionerRepository;
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
public class PractitionerRepositoryTest {
    /* Gleich wie PatientControllerRepository erklärung angeben*/

    @Autowired
    private PractitionerRepository practitionerRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/practitioner.json";
    private Practitioner testPractitioner;

    @BeforeEach
    public void setUp() {
        testPractitioner = loadTestData(TEST_DATA_PATH, Practitioner.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePractitioner() {
        Practitioner savedPractitioner = practitionerRepository.save(testPractitioner);
        String id = savedPractitioner.getId();
        Practitioner loadedPractitioner = practitionerRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedPractitioner, savedPractitioner);
    }

    @Test
    @Transactional
    public void testDeleteOnePractitioner() {
        Practitioner savedPractitioner = practitionerRepository.save(testPractitioner);
        practitionerRepository.deleteById(savedPractitioner.getId());
        boolean exists = practitionerRepository.existsById(savedPractitioner.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePractitioner() {
        Practitioner savedEmptyPractitioner = practitionerRepository.save(new Practitioner());
        String savedId = savedEmptyPractitioner.getId();
        BeanUtils.copyProperties(testPractitioner, savedEmptyPractitioner);
        savedEmptyPractitioner.setId(savedId);
        practitionerRepository.save(savedEmptyPractitioner);
        Practitioner loadedPractitioner = practitionerRepository.findById(savedEmptyPractitioner.getId()).orElseThrow();
        assertEntityEquals(loadedPractitioner, testPractitioner);
    }

}