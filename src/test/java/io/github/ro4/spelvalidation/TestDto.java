package io.github.ro4.spelvalidation;

import javax.validation.constraints.NotNull;

@SpELAssert(expression = "#p.maxAge > #p.minAge", message = "max age must greater than min age")
@SuppressWarnings("unused")
public class TestDto {
    @SpELAssert(expression = "@simpleService.isNameUnique(#p)", message = "name already exists")
    private String name;

    @NotNull
    private Integer maxAge;

    private Integer minAge;

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
