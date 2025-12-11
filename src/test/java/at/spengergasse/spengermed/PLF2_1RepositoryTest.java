package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.PLF2_1;
import at.spengergasse.spengermed.repository.PLF2_1Repository;
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
public class PLF2_1RepositoryTest {

    @Autowired
    private PLF2_1Repository plf2_1Repository;

    private static final String TEST_DATA_PATH = "src/test/resources/plf2_1.json";
    private PLF2_1 testPLF2_1;

    @BeforeEach
    public void setUp() {
        testPLF2_1 = loadTestData(TEST_DATA_PATH, PLF2_1.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePLF2_1() {
        PLF2_1 savedPLF2_1 = plf2_1Repository.save(testPLF2_1);
        String id = savedPLF2_1.getId();
        PLF2_1 loadedPLF2_1 = plf2_1Repository.findById(id).orElseThrow();
        assertEntityEquals(loadedPLF2_1, savedPLF2_1);
    }

    @Test
    @Transactional
    public void testDeleteOnePLF2_1() {
        PLF2_1 savedPLF2_1 = plf2_1Repository.save(testPLF2_1);
        plf2_1Repository.deleteById(savedPLF2_1.getId());
        boolean exists = plf2_1Repository.existsById(savedPLF2_1.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePLF2_1() {
        PLF2_1 savedEmptyPLF2_1 = plf2_1Repository.save(new PLF2_1());
        String savedId = savedEmptyPLF2_1.getId();
        BeanUtils.copyProperties(testPLF2_1, savedEmptyPLF2_1);
        savedEmptyPLF2_1.setId(savedId);
        plf2_1Repository.save(savedEmptyPLF2_1);
        PLF2_1 loadedPLF2_1 = plf2_1Repository.findById(savedEmptyPLF2_1.getId()).orElseThrow();
        assertEntityEquals(loadedPLF2_1, testPLF2_1);
    }

}