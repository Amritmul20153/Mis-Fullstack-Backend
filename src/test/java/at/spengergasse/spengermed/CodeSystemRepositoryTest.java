package at.spengergasse.spengermed;

import at.spengergasse.spengermed.CodeSystem.CodeSystem;
import org.springframework.boot.test.context.SpringBootTest;


import at.spengergasse.spengermed.repository.CodeSystemRepository;
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
public class CodeSystemRepositoryTest {

    @Autowired
    private CodeSystemRepository codeSystemRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/codesystem_test_data.json";
    private CodeSystem testCodeSystem;

    @BeforeEach
    public void setUp() {
        testCodeSystem = loadTestData(TEST_DATA_PATH, CodeSystem.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneCodeSystem() {
        CodeSystem savedCodeSystem = codeSystemRepository.save(testCodeSystem);
        String id = savedCodeSystem.getId();
        CodeSystem loadedCodeSystem = codeSystemRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedCodeSystem, savedCodeSystem);
    }

    @Test
    @Transactional
    public void testDeleteOneCodeSystem() {
        CodeSystem savedCodeSystem = codeSystemRepository.save(testCodeSystem);
        codeSystemRepository.deleteById(savedCodeSystem.getId());
        boolean exists = codeSystemRepository.existsById(savedCodeSystem.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneCodeSystem() {
        CodeSystem savedEmptyCodeSystem = codeSystemRepository.save(new CodeSystem());
        String savedId = savedEmptyCodeSystem.getId();
        BeanUtils.copyProperties(testCodeSystem, savedEmptyCodeSystem);
        savedEmptyCodeSystem.setId(savedId);
        codeSystemRepository.save(savedEmptyCodeSystem);
        CodeSystem loadedCodeSystem = codeSystemRepository.findById(savedEmptyCodeSystem.getId()).orElseThrow();
        assertEntityEquals(loadedCodeSystem, testCodeSystem);
    }
}
