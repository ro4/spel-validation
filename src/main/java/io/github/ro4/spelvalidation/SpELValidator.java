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

public class SpELValidator implements ConstraintValidator<SpELCheck, Object>, BeanFactoryAware {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    private String expression;

    private StandardEvaluationContext spELCtx;

    private BeanFactory beanFactory;

    private String variableName;

    @Override
    public void initialize(SpELCheck anno) {
        ConstraintValidator.super.initialize(anno);
        if (!ObjectUtils.isEmpty(anno.expression())) {
            expression = StringUtils.arrayToDelimitedString(anno.expression(), " ");
        } else if (!ObjectUtils.isEmpty(anno.value())) {
            expression = anno.value();
        }
        this.spELCtx = new StandardEvaluationContext();
        spELCtx.setBeanResolver(new BeanFactoryResolver(beanFactory));
        this.variableName = anno.variableName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        spELCtx.setVariable(variableName, value);
        return Boolean.TRUE.equals(PARSER.parseExpression(expression).getValue(spELCtx, Boolean.TYPE));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
