package io.github.ro4.spelvalidation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Configuration
public class TestConfiguration {
    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public SimpleController simpleController() {
        return new SimpleController();
    }

    @Bean
    public ValidService validService() {
        return new ValidServiceImpl();
    }

    @Service
    @SuppressWarnings("unused")
    public static class SimpleService {
        public boolean isNameUnique(String name) {
            return true;
        }
    }

    @Component
    @Validated
    public static class SimpleController {
        @Validated
        public String save(@Valid TestDto dto) {
            return dto.getName();
        }
    }
}
