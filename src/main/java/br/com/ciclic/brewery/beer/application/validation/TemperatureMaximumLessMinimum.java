package br.com.ciclic.brewery.beer.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TemperatureValidator.class })
public @interface TemperatureMaximumLessMinimum {

    String message() default "The maximum value is lower minimum.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}