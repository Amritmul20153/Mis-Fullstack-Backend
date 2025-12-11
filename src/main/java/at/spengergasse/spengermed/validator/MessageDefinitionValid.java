package at.spengergasse.spengermed.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MessageDefinitionValidator.class})
@Documented
public @interface MessageDefinitionValid {
    String message() default "MessageDefinition.url cannot contain # or |";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
