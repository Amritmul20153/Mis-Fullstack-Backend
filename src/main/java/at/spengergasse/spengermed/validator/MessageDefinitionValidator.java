package at.spengergasse.spengermed.validator;

import at.spengergasse.spengermed.model.MessageDefinition;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MessageDefinitionValidator implements ConstraintValidator<MessageDefinitionValid, MessageDefinition> {

    @Override
    public void initialize(MessageDefinitionValid constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MessageDefinition messageDefinition, ConstraintValidatorContext constraintValidatorContext) {
        if (messageDefinition.getUrl() != null) {
            if (messageDefinition.getUrl().contains("#") || messageDefinition.getUrl().contains("|")) {
                return false;
            }
        }
        return true;

    }
}
