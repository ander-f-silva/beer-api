package br.com.ciclic.brewery.beer.application.validation;

import br.com.ciclic.brewery.beer.application.transferobject.TemperatureTransferObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TemperatureValidator implements ConstraintValidator<TemperatureMaximumLessMinimum, TemperatureTransferObject> {

    @Override
    public void initialize(TemperatureMaximumLessMinimum constraintAnnotation) {   }

    @Override
    public boolean isValid(TemperatureTransferObject value, ConstraintValidatorContext context) {
        if (value == null) return false;

        if (value.getMaximum() == null || value.getMinimum() == null) return false;

        return value.getMaximum() >= value.getMinimum();
    }
}
