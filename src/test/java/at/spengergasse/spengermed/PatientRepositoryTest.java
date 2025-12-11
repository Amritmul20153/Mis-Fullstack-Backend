package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Patient;
import at.spengergasse.spengermed.repository.PatientRepository;
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
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/patient.json";
    private Patient testPatient;

    @BeforeEach
    public void setUp() {
        testPatient = loadTestData(TEST_DATA_PATH, Patient.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOnePatient() {
        Patient savedPatient = patientRepository.save(testPatient);
        String id = savedPatient.getId();
        Patient loadedPatient = patientRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedPatient, savedPatient);
    }

    @Test
    @Transactional
    public void testDeleteOnePatient() {
        Patient savedPatient = patientRepository.save(testPatient);
        patientRepository.deleteById(savedPatient.getId());
        boolean exists = patientRepository.existsById(savedPatient.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOnePatient() {
        Patient savedEmptyPatient = patientRepository.save(new Patient());
        String savedId = savedEmptyPatient.getId();
        BeanUtils.copyProperties(testPatient, savedEmptyPatient);
        savedEmptyPatient.setId(savedId);
        patientRepository.save(savedEmptyPatient);
        Patient loadedPatient = patientRepository.findById(savedEmptyPatient.getId()).orElseThrow();
        assertEntityEquals(loadedPatient, testPatient);
    }

}