package io.github.ro4.spelvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpELValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SpELAssert.List.class)
public @interface SpELAssert {

    String value() default "";

    String[] expression() default "";

    String message() default "{io.github.ro4.spelvalidation.SpELAssert.message}";

    String alias() default "p";

    String reportOn() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        SpELAssert[] value();
    }
}
