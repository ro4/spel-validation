package io.github.ro4.spelvalidation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Objects;

public class SpELAssertTest {
    private SpELValidator spELValidator;

    private ConstraintValidatorContext validatorContext;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        spELValidator = context.getBean("spELValidator", SpELValidator.class);
        validatorContext = new DummyConstraintValidatorContext();
    }

    @Test
    public void testRegular() {
        TestDto dto = new TestDto();
        dto.setName("John");
        dto.setMaxAge(10);
        dto.setMinAge(11);
        SpELAssert spELAssert = AnnotationUtils.findAnnotation(TestDto.class, SpELAssert.class);
        spELValidator.initialize(spELAssert);
        Assert.assertFalse(spELValidator.isValid(dto, validatorContext));
        dto.setMinAge(9);
        Assert.assertTrue(spELValidator.isValid(dto, validatorContext));
    }

    @Test
    public void testCallBean() {
        Annotation[] annotations = ReflectionUtils.findField(TestDto.class, "name").getAnnotations();
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof SpELAssert)) {
                continue;
            }
            spELValidator.initialize((SpELAssert) annotation);
            Assert.assertFalse(spELValidator.isValid("hello1", validatorContext));
            Assert.assertTrue(spELValidator.isValid("hello", validatorContext));
        }
    }


    @Test
    public void testOnlyValue() {
        SpELAssert spELAssert = ReflectionUtils.findField(TestDto.class, "maxAge").getAnnotation(SpELAssert.class);
        spELValidator.initialize(spELAssert);
        Assert.assertFalse(spELValidator.isValid(101, validatorContext));
        Assert.assertTrue(spELValidator.isValid(100, validatorContext));
    }

    @Test
    public void testOther() {
        Annotation[] annotations = ReflectionUtils.findField(TestDto.class, "minAge").getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof SpELAssert.List)) {
                continue;
            }
            for (SpELAssert spELAssert : ((SpELAssert.List) annotation).value()) {
                spELValidator.initialize(spELAssert);
                if (Objects.equals(spELAssert.value(), "true")) {
                    Assert.assertTrue(spELValidator.isValid("anything", validatorContext));
                } else {
                    Assert.assertFalse(spELValidator.isValid(111, validatorContext));
                    Assert.assertTrue(spELValidator.isValid(109, validatorContext));
                }
            }
        }
    }

}
