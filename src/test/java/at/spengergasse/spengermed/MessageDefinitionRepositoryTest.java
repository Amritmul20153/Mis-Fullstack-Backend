package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.MessageDefinition;
import at.spengergasse.spengermed.repository.MessageDefinitionRepository;
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
public class MessageDefinitionRepositoryTest {

    @Autowired
    private MessageDefinitionRepository messagedefinitionRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/messagedefinition.json";
    private MessageDefinition testMessageDefinition;

    @BeforeEach
    public void setUp() {
        testMessageDefinition = loadTestData(TEST_DATA_PATH, MessageDefinition.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneMessageDefinition() {
        MessageDefinition savedMessageDefinition = messagedefinitionRepository.save(testMessageDefinition);
        String id = savedMessageDefinition.getId();
        MessageDefinition loadedMessageDefinition = messagedefinitionRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedMessageDefinition, savedMessageDefinition);
    }

    @Test
    @Transactional
    public void testDeleteOneMessageDefinition() {
        MessageDefinition savedMessageDefinition = messagedefinitionRepository.save(testMessageDefinition);
        messagedefinitionRepository.deleteById(savedMessageDefinition.getId());
        boolean exists = messagedefinitionRepository.existsById(savedMessageDefinition.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneMessageDefinition() {
        MessageDefinition savedEmptyMessageDefinition = messagedefinitionRepository.save(new MessageDefinition());
        String savedId = savedEmptyMessageDefinition.getId();
        BeanUtils.copyProperties(testMessageDefinition, savedEmptyMessageDefinition);
        savedEmptyMessageDefinition.setId(savedId);
        messagedefinitionRepository.save(savedEmptyMessageDefinition);
        MessageDefinition loadedMessageDefinition = messagedefinitionRepository.findById(savedEmptyMessageDefinition.getId()).orElseThrow();
        assertEntityEquals(loadedMessageDefinition, testMessageDefinition);
    }

}