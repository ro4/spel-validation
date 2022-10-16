package io.github.ro4.spelvalidation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class ValidServiceImpl implements ValidService {
    @Override
    public String save(@Valid TestDto dto) {
        return dto.getName();
    }
}
