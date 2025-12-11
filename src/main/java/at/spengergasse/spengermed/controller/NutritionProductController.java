package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.NutritionProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nutritionproduct/")
public class NutritionProductController extends BaseController<NutritionProduct> {

    public NutritionProductController(CrudRepository<NutritionProduct, String> repository) {
        super(repository);
    }
}
