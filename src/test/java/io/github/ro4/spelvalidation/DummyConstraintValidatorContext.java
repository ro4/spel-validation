package io.github.ro4.spelvalidation;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;

public class DummyConstraintValidatorContext implements ConstraintValidatorContext {
    @Override
    public void disableDefaultConstraintViolation() {

    }

    @Override
    public String getDefaultConstraintMessageTemplate() {
        return null;
    }

    @Override
    public ClockProvider getClockProvider() {
        return null;
    }

    @Override
    public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }
}
