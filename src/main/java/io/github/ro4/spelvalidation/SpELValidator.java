package io.github.ro4.spelvalidation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpELValidator implements ConstraintValidator<SpELAssert, Object>, BeanFactoryAware {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    private String expression;

    private StandardEvaluationContext spELCtx;

    private BeanFactory beanFactory;

    private String alias;

    private String message;

    private String reportOn;

    @Override
    public void initialize(SpELAssert anno) {
        ConstraintValidator.super.initialize(anno);
        if (!ObjectUtils.isEmpty(anno.expression())) {
            expression = StringUtils.arrayToDelimitedString(anno.expression(), " ");
        } else if (!ObjectUtils.isEmpty(anno.value())) {
            expression = anno.value();
        }
        this.spELCtx = new StandardEvaluationContext();
        spELCtx.setBeanResolver(new BeanFactoryResolver(beanFactory));
        this.alias = anno.alias();
        this.message = anno.message();
        this.reportOn = anno.reportOn();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result = false;
        if (value != null) {
            spELCtx.setVariable(alias, value);
            result = Boolean.TRUE.equals(PARSER.parseExpression(expression).getValue(spELCtx, Boolean.TYPE));
        }
        if (!result && !reportOn.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(reportOn).addConstraintViolation();
        }
        return result;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
