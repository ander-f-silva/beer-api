package br.com.ciclic.brewery.beer.application.validation;

import br.com.ciclic.brewery.beer.application.transferobject.TemperatureTransferObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TemperatureValidator implements ConstraintValidator<TemperatureMaximumLessMinimum, TemperatureTransferObject> {

    private final Logger logger = LoggerFactory.getLogger(TemperatureValidator.class);

    @Override
    public void initialize(TemperatureMaximumLessMinimum constraintAnnotation) { logger.info("'initialize' not implementation!"); }

    @Override
    public boolean isValid(TemperatureTransferObject value, ConstraintValidatorContext context) {
        if (value == null) return false;
        if (value.getMaximum() == null || value.getMinimum() == null) return false;
        return value.getMaximum() >= value.getMinimum();
    }
}
