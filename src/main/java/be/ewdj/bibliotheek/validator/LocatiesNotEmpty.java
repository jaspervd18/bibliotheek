package be.ewdj.bibliotheek.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = LocatiesNotEmptyValidator.class)
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface LocatiesNotEmpty {

    String message() default "{locaties.lijst.nietLeeg}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
