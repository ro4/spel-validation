package io.github.ro4.spelvalidation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpELAssertTest {

    private TestConfiguration.SimpleController controller;

    private ValidService validService;

    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        controller = context.getBean("simpleController", TestConfiguration.SimpleController.class);
        validService = context.getBean("validService", ValidService.class);
    }

    @Test
    public void testRegular() {
        TestDto dto = new TestDto();
        dto.setName("John");
//        dto.setMaxAge(10);
//        dto.setMinAge(11);

        Assert.assertEquals(dto.getName(), validService.save(dto));
    }

}
