package be.ewdj.bibliotheek.validator;

import java.util.List;

import be.ewdj.bibliotheek.models.Auteur;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuteursNotEmptyValidator implements ConstraintValidator<AuteursNotEmpty, List<Auteur>> {
    @Override
    public void initialize(AuteursNotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Auteur> objects, ConstraintValidatorContext context) {
        if (objects == null || objects.isEmpty()) {
            return false;
        }

        for (Auteur auteur : objects) {
            if (auteur != null && isValidAuteur(auteur)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidAuteur(Auteur auteur) {
        return auteur.getNaam() != null && !auteur.getNaam().isEmpty()
                && auteur.getVoornaam() != null && !auteur.getVoornaam().isEmpty();
    }

}
