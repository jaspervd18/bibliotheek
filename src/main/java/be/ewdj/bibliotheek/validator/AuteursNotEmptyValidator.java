package be.ewdj.bibliotheek.validator;

import java.util.List;

import be.ewdj.bibliotheek.auteur.Auteur;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuteursNotEmptyValidator implements ConstraintValidator<AuteursNotEmpty, List<Auteur>> {
    @Override
    public void initialize(AuteursNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Auteur> objects, ConstraintValidatorContext context) {
        return objects != null && !objects.isEmpty();
    }

}
