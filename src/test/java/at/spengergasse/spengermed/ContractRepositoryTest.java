package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Contract;
import at.spengergasse.spengermed.repository.ContractRepository;
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
public class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/contract.json";
    private Contract testContract;

    @BeforeEach
    public void setUp() {
        testContract = loadTestData(TEST_DATA_PATH, Contract.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneContract() {
        Contract savedContract = contractRepository.save(testContract);
        String id = savedContract.getId();
        Contract loadedContract = contractRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedContract, savedContract);
    }

    @Test
    @Transactional
    public void testDeleteOneContract() {
        Contract savedContract = contractRepository.save(testContract);
        contractRepository.deleteById(savedContract.getId());
        boolean exists = contractRepository.existsById(savedContract.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneContract() {
        Contract savedEmptyContract = contractRepository.save(new Contract());
        String savedId = savedEmptyContract.getId();
        BeanUtils.copyProperties(testContract, savedEmptyContract);
        savedEmptyContract.setId(savedId);
        contractRepository.save(savedEmptyContract);
        Contract loadedContract = contractRepository.findById(savedEmptyContract.getId()).orElseThrow();
        assertEntityEquals(loadedContract, testContract);
    }

}