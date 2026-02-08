package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Observation;
import at.spengergasse.spengermed.repository.ObservationRepository;
import at.spengergasse.spengermed.repository.ObservationRepository;
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
public class ObservationRepositoryTest {

    @Autowired
    private ObservationRepository observationRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/observation.json";
    private Observation testObservation;

    @BeforeEach
    public void setUp() {
        testObservation = loadTestData(TEST_DATA_PATH, Observation.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneObservation() {
        Observation savedObservation = observationRepository.save(testObservation);
        String id = savedObservation.getId();
        Observation loadedObservation = observationRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedObservation, savedObservation);
    }

    @Test
    @Transactional
    public void testDeleteOneObservation() {
        Observation savedObservation = observationRepository.save(testObservation);
        observationRepository.deleteById(savedObservation.getId());
        boolean exists = observationRepository.existsById(savedObservation.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneObservation() {
        Observation savedEmptyObservation = observationRepository.save(new Observation());
        String savedId = savedEmptyObservation.getId();
        BeanUtils.copyProperties(testObservation, savedEmptyObservation);
        savedEmptyObservation.setId(savedId);
        observationRepository.save(savedEmptyObservation);
        Observation loadedObservation = observationRepository.findById(savedEmptyObservation.getId()).orElseThrow();
        assertEntityEquals(loadedObservation, testObservation);
    }

}