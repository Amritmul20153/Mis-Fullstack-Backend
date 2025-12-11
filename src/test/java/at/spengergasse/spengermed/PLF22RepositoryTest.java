package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.PLF22;
import at.spengergasse.spengermed.repository.PLF22Repository;
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
public class PLF22RepositoryTest {

    @Autowired
    private PLF22Repository plf22Repository;

    private static final String TEST_DATA_PATH = "src/test/resources/plf22.json";
    private PLF22 testPLF22;

    @BeforeEach
    public void setUp() {
        testPLF22 = loadTestData(TEST_DATA_PATH, PLF22.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePLF22() {
        PLF22 savedPLF22 = plf22Repository.save(testPLF22);
        String id = savedPLF22.getId();
        PLF22 loadedPLF22 = plf22Repository.findById(id).orElseThrow();
        assertEntityEquals(loadedPLF22, savedPLF22);
    }

    @Test
    @Transactional
    public void testDeleteOnePLF22() {
        PLF22 savedPLF22 = plf22Repository.save(testPLF22);
        plf22Repository.deleteById(savedPLF22.getId());
        boolean exists = plf22Repository.existsById(savedPLF22.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePLF22() {
        PLF22 savedEmptyPLF22 = plf22Repository.save(new PLF22());
        String savedId = savedEmptyPLF22.getId();
        BeanUtils.copyProperties(testPLF22, savedEmptyPLF22);
        savedEmptyPLF22.setId(savedId);
        plf22Repository.save(savedEmptyPLF22);
        PLF22 loadedPLF22 = plf22Repository.findById(savedEmptyPLF22.getId()).orElseThrow();
        assertEntityEquals(loadedPLF22, testPLF22);
    }

}