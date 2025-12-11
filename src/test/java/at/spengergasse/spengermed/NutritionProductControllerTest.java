package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.NutritionProduct;
import at.spengergasse.spengermed.repository.NutritionProductRepository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class NutritionProductControllerTest extends AbstractControllerTest {
    @Autowired
    NutritionProductRepository nutritionProductRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/nutritionProduct.json"; // Hier den Pfad anpassen
    private final NutritionProduct testNutritionProduct = loadTestData(TEST_DATA_PATH, NutritionProduct.class);
    private final String endpoint = "/api/nutritionproduct/"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllNutritionProducts() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetANutritionProduct() throws Exception {
        String id = nutritionProductRepository.save(testNutritionProduct).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostANutritionProduct() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testNutritionProduct);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutANutritionProduct() throws Exception {
        String id = nutritionProductRepository.save(new NutritionProduct()).getId();
        String jsonContent = objectMapper.writeValueAsString(testNutritionProduct);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteANutritionProduct() throws Exception {
        String id = testNutritionProduct.getId();
        performDeleteRequest("/api/nutritionProduct/" + id);
    }
}