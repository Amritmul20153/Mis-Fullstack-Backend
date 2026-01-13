package at.spengergasse.spengermed.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PLF1_1Validator.class})
@Documented
public @interface PLF1_1Valid {
    String message() default "PLF1_1.url cannot contain # or |";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


