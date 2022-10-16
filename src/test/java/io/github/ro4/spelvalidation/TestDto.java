package io.github.ro4.spelvalidation;

@SpELAssert(expression = "#p.maxAge > #p.minAge", message = "max age must greater than min age")
@SuppressWarnings("unused")
public class TestDto {
    @SpELAssert(expression = "@simpleService.isNameUnique(#p)", message = "name already exists")
    protected String name;

    @SpELAssert("#p == 100")
    protected Integer maxAge;

    @SpELAssert(expression = {"#_ > 100", "and #_ < 110"}, alias = "_", message = "ff")
    @SpELAssert("true")
    protected Integer minAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
