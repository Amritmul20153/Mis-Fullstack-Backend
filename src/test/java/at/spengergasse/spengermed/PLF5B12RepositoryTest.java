package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.PLF5B12;
import at.spengergasse.spengermed.repository.EncounterRepository;
import at.spengergasse.spengermed.repository.PLF5B12Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.assertEntityEquals;
import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PLF5B12RepositoryTest {

    @Autowired
    private PLF5B12Repository plf5_b12Repository;

    private static final String TEST_DATA_PATH = "src/test/resources/plf5_b12.json";
    private PLF5B12 testPLF5B12;

    @BeforeEach
    public void setUp() {
        testPLF5B12 = loadTestData(TEST_DATA_PATH, PLF5B12.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePLF5B12() {
        PLF5B12 savedPLF5B12 = plf5_b12Repository.save(testPLF5B12);
        String id = savedPLF5B12.getId();
        PLF5B12 loadedPLF5B12 = plf5_b12Repository.findById(id).orElseThrow();
        assertEntityEquals(loadedPLF5B12, savedPLF5B12);
    }

    @Test
    @Transactional
    public void testDeleteOnePLF5B12() {
        PLF5B12 savedPLF5B12 = plf5_b12Repository.save(testPLF5B12);
        plf5_b12Repository.deleteById(savedPLF5B12.getId());
        boolean exists = plf5_b12Repository.existsById(savedPLF5B12.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePLF5B12() {
        PLF5B12 savedEmptyPLF5B12 = plf5_b12Repository.save(new PLF5B12());
        String savedId = savedEmptyPLF5B12.getId();
        BeanUtils.copyProperties(testPLF5B12, savedEmptyPLF5B12);
        savedEmptyPLF5B12.setId(savedId);
        plf5_b12Repository.save(savedEmptyPLF5B12);
        PLF5B12 loadedPLF5B12 = plf5_b12Repository.findById(savedEmptyPLF5B12.getId()).orElseThrow();
        assertEntityEquals(loadedPLF5B12, testPLF5B12);
    }

}

