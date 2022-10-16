package io.github.ro4.spelvalidation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class TestConfiguration {
    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public SpELValidator spELValidator() {
        return new SpELValidator();
    }


    @Service
    @SuppressWarnings("unused")
    public static class SimpleService {
        public boolean isNameUnique(String name) {
            return "hello".equals(name);
        }
    }
}
