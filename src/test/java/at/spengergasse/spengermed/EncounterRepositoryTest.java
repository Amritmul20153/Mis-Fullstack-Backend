package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.repository.EncounterRepository;
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
public class EncounterRepositoryTest {

    @Autowired
    private EncounterRepository encounterRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/encounter.json";
    private Encounter testEncounter;

    @BeforeEach
    public void setUp() {
        testEncounter = loadTestData(TEST_DATA_PATH, Encounter.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneEncounter() {
        Encounter savedEncounter = encounterRepository.save(testEncounter);
        String id = savedEncounter.getId();
        Encounter loadedEncounter = encounterRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedEncounter, savedEncounter);
    }

    @Test
    @Transactional
    public void testDeleteOneEncounter() {
        Encounter savedEncounter = encounterRepository.save(testEncounter);
        encounterRepository.deleteById(savedEncounter.getId());
        boolean exists = encounterRepository.existsById(savedEncounter.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneEncounter() {
        Encounter savedEmptyEncounter = encounterRepository.save(new Encounter());
        String savedId = savedEmptyEncounter.getId();
        BeanUtils.copyProperties(testEncounter, savedEmptyEncounter);
        savedEmptyEncounter.setId(savedId);
        encounterRepository.save(savedEmptyEncounter);
        Encounter loadedEncounter = encounterRepository.findById(savedEmptyEncounter.getId()).orElseThrow();
        assertEntityEquals(loadedEncounter, testEncounter);
    }

}