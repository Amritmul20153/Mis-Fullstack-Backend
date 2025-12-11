package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.NutritionProduct;
import org.springframework.data.repository.ListCrudRepository;

public interface NutritionProductRepository extends ListCrudRepository<NutritionProduct, String> {
}
