/*package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Encounter;
import at.spengergasse.spengermed.model.PLF1_1;
import at.spengergasse.spengermed.repository.PLF1_1Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.assertEntityEquals;
import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PLF1_1RepositoryTest {

    @Autowired
    private PLF1_1Repository plf1_1Repository;

    private static final String TEST_DATA_PATH = "src/test/resources/plf1_1.json";
    private PLF1_1 testPLF1_1;

    @BeforeEach
    public void setUp() {
        testPLF1_1 = loadTestData(TEST_DATA_PATH, PLF1_1.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePLF1_1() {
        PLF1_1 savedPLF1_1 = plf1_1Repository.save(testPLF1_1);
        String id = savedPLF1_1.getId();
        PLF1_1 loadedPLF1_1 = plf1_1Repository.findById(id).orElseThrow();
        assertEntityEquals(loadedPLF1_1, savedPLF1_1);
    }

    @Test
    @Transactional
    public void testDeleteOnePLF1_1() {
        PLF1_1 savedPLF1_1 = plf1_1Repository.save(testPLF1_1);
        plf1_1Repository.deleteById(savedPLF1_1.getId());
        boolean exists = plf1_1Repository.existsById(savedPLF1_1.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePLF1_1() {
        PLF1_1 savedEmptyPLF1_1 = plf1_1Repository.save(new PLF1_1());
        String savedId = savedEmptyPLF1_1.getId();
        BeanUtils.copyProperties(testPLF1_1, savedEmptyPLF1_1);
        savedEmptyPLF1_1.setId(savedId);
        plf1_1Repository.save(savedEmptyPLF1_1);
        PLF1_1 loadedPLF1_1 = plf1_1Repository.findById(savedEmptyPLF1_1.getId()).orElseThrow();
        assertEntityEquals(loadedPLF1_1, testPLF1_1);
    }


}


 */