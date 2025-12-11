package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Medication;
import at.spengergasse.spengermed.repository.MedicationRepository;
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
public class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/medication.json";
    private Medication testMedication;

    @BeforeEach
    public void setUp() {
        testMedication = loadTestData(TEST_DATA_PATH, Medication.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneMedication() {
        Medication savedMedication = medicationRepository.save(testMedication);
        String id = savedMedication.getId();
        Medication loadedMedication = medicationRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedMedication, savedMedication);
    }

    @Test
    @Transactional
    public void testDeleteOneMedication() {
        Medication savedMedication = medicationRepository.save(testMedication);
        medicationRepository.deleteById(savedMedication.getId());
        boolean exists = medicationRepository.existsById(savedMedication.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneMedication() {
        Medication savedEmptyMedication = medicationRepository.save(new Medication());
        String savedId = savedEmptyMedication.getId();
        BeanUtils.copyProperties(testMedication, savedEmptyMedication);
        savedEmptyMedication.setId(savedId);
        medicationRepository.save(savedEmptyMedication);
        Medication loadedMedication = medicationRepository.findById(savedEmptyMedication.getId()).orElseThrow();
        assertEntityEquals(loadedMedication, testMedication);
    }

}