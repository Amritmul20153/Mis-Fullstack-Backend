package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.NutritionProduct;
import at.spengergasse.spengermed.repository.NutritionProductRepository;
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
public class NutritionProductRepositoryTest {

    @Autowired
    private NutritionProductRepository nutritionProductRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/nutritionProduct.json";
    private NutritionProduct testNutritionProduct;

    @BeforeEach
    public void setUp() {
        testNutritionProduct = loadTestData(TEST_DATA_PATH, NutritionProduct.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneNutritionProduct() {
        NutritionProduct savedNutritionProduct = nutritionProductRepository.save(testNutritionProduct);
        String id = savedNutritionProduct.getId();
        NutritionProduct loadedNutritionProduct = nutritionProductRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedNutritionProduct, savedNutritionProduct);
    }

    @Test
    @Transactional
    public void testDeleteOneNutritionProduct() {
        NutritionProduct savedNutritionProduct = nutritionProductRepository.save(testNutritionProduct);
        nutritionProductRepository.deleteById(savedNutritionProduct.getId());
        boolean exists = nutritionProductRepository.existsById(savedNutritionProduct.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneNutritionProduct() {
        NutritionProduct savedEmptyNutritionProduct = nutritionProductRepository.save(new NutritionProduct());
        String savedId = savedEmptyNutritionProduct.getId();
        BeanUtils.copyProperties(testNutritionProduct, savedEmptyNutritionProduct);
        savedEmptyNutritionProduct.setId(savedId);
        nutritionProductRepository.save(savedEmptyNutritionProduct);
        NutritionProduct loadedNutritionProduct = nutritionProductRepository.findById(savedEmptyNutritionProduct.getId()).orElseThrow();
        assertEntityEquals(loadedNutritionProduct, testNutritionProduct);
    }

}