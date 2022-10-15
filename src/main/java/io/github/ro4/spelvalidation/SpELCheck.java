package io.github.ro4.spelvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpELValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SpELChecks.class)
public @interface SpELCheck {

    String value() default "";

    String[] expression() default "";

    String message() default "{io.github.ro4.spelvalidation.SpELCheck.message}";

    String variableName() default "p";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
