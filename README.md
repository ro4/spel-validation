## SpEL Validation

This is a custom validator based on `Jakarta validation-api`, inspired by `org.hibernate.validator.constraints.ScriptAssert`.  
The source code is so tiny, you can read it as document :)

### Example

```java

@SpELAssert("#p.maxAge > 100") // repeatable annotation
@SpELAssert(expression = "#_.maxAge > 100", alias = "_") // alias support
@SpELAssert(
        expression = {"#p.minAge < #p.maxAge", "and #p.minAge > 10"}, // multi-line expressions
        message = "Max must greater than min age, min age must greater than 10",
        reportOn = "minAge"
)
public class Config {

    @SpELAssert("@springBean.method(#p)") // spring bean invoke
    private String name;

    private Integer minAge;

    private Integer maxAge;
}

```