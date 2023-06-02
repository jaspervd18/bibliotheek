package be.ewdj.bibliotheek.validator;

import java.util.List;

import be.ewdj.bibliotheek.models.Locatie;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LocatiesNotEmptyValidator implements ConstraintValidator<LocatiesNotEmpty, List<Locatie>> {
    @Override
    public void initialize(LocatiesNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Locatie> objects, ConstraintValidatorContext context) {
        return objects != null && !objects.isEmpty();
    }

}
