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
        if (objects == null || objects.isEmpty()) {
            return false;
        }

        for (Locatie locatie : objects) {
            if (locatie != null && isValidLocatie(locatie)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidLocatie(Locatie locatie) {
        return locatie.getCode1() != 0 && locatie.getCode2() != 0
                && locatie.getPlaatsnaam() != null && !locatie.getPlaatsnaam().isEmpty();
    }

}
