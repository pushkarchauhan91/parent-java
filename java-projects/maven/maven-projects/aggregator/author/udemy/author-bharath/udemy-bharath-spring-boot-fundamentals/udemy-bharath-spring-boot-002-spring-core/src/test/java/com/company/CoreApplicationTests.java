package com.company;

import com.company.services.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    PaymentServiceImpl service;

    @Test
    void testDependencyInjection() {
        assertNotNull(service.getDao());
    }

}
