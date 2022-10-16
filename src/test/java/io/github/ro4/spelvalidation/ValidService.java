package io.github.ro4.spelvalidation;

import javax.validation.Valid;

public interface ValidService {
    String save(@Valid @SpELAssert() TestDto dto);
}
