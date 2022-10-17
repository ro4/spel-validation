package io.github.ro4.spelvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This is a custom validator based on `Jakarta validation-api`, inspired by `org.hibernate.validator.constraints.ScriptAssert`.
 * <pre>
 * {@code @SpELAssert("#p.maxAge > 100") // repeatable annotation}
 * {@code @SpELAssert(expression = "#_.maxAge > 100", alias = "_") // alias support}
 * {@code @SpELAssert(
 *         expression = {"#p.minAge < #p.maxAge", "and #p.minAge > 10"}, // multi-line expressions
 *         message = "Max must greater than min age, min age must greater than 10",
 *         reportOn = "minAge"
 * )}
 * {@code @SpELAssert("@springBean.method(#p.name)") // spring bean invoke}
 * public class Config {
 *
 *     private String name;
 *
 *     private Integer minAge;
 *
 *     private Integer maxAge;
 * }
 * </pre>
 *
 * @author ro4
 */
@Documented
@Constraint(validatedBy = SpELValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SpELAssert.List.class)
public @interface SpELAssert {

    String value() default "";

    String[] expression() default {};

    String message() default "{io.github.ro4.spelvalidation.SpELAssert.message}";

    /**
     * @return The name, under which the annotated element shall be registered
     * within the SpEL context. Defaults to "p".
     */
    String alias() default "p";


    /**
     * @return The name of the property for which you would like to report a validation error.
     * If given, the resulting constraint violation will be reported on the specified property.
     * If not given, the constraint violation will be reported on the annotated bean.
     */
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
