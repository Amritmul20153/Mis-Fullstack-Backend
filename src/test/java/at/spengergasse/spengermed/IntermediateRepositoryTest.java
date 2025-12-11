package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Intermediate;
import at.spengergasse.spengermed.repository.IntermediateRepository;
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
public class IntermediateRepositoryTest {

    @Autowired
    private IntermediateRepository intermediateRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/intermediate.json";
    private Intermediate testIntermediate;

    @BeforeEach
    public void setUp() {
        testIntermediate = loadTestData(TEST_DATA_PATH, Intermediate.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneIntermediate() {
        Intermediate savedIntermediate = intermediateRepository.save(testIntermediate);
        String id = savedIntermediate.getId();
        Intermediate loadedIntermediate = intermediateRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedIntermediate, savedIntermediate);
    }

    @Test
    @Transactional
    public void testDeleteOneIntermediate() {
        Intermediate savedIntermediate = intermediateRepository.save(testIntermediate);
        intermediateRepository.deleteById(savedIntermediate.getId());
        boolean exists = intermediateRepository.existsById(savedIntermediate.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneIntermediate() {
        Intermediate savedEmptyIntermediate = intermediateRepository.save(new Intermediate());
        String savedId = savedEmptyIntermediate.getId();
        BeanUtils.copyProperties(testIntermediate, savedEmptyIntermediate);
        savedEmptyIntermediate.setId(savedId);
        intermediateRepository.save(savedEmptyIntermediate);
        Intermediate loadedIntermediate = intermediateRepository.findById(savedEmptyIntermediate.getId()).orElseThrow();
        assertEntityEquals(loadedIntermediate, testIntermediate);
    }

}