package at.spengergasse.spengermed.validator;

import at.spengergasse.spengermed.model.HumanName;
import at.spengergasse.spengermed.model.MessageDefinition;
import at.spengergasse.spengermed.model.PLF1_1;
import at.spengergasse.spengermed.model.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PLF1_1Validator implements ConstraintValidator<PLF1_1Valid, PLF1_1> {


    @Override
    public void initialize(PLF1_1Valid constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

   /* @Override
    public boolean isValid(MessageDefinition messageDefinition, ConstraintValidatorContext constraintValidatorContext) {
        if (messageDefinition.getUrl() != null) {
            if (messageDefinition.getUrl().contains("#") || messageDefinition.getUrl().contains("|")) {
                return false;
            }
        }
        return true;

    }

    */

    @Override
    public boolean isValid(PLF1_1 plf11, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Column(name = "plf1_vs")
    private String vs;

    @JoinColumn(name = "plf_vdt")
    private LocalDateTime vdt;
}


