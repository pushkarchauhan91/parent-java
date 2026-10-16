package com.company.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GreetingServiceImplTest {

    private Greeting greeting;

    @BeforeEach
    void setUp() {
        greeting = new GreetingServiceImpl();
    }

    @Test
    void greetShouldReturnValidOutput() {
        String result = greeting.greet("Pushkar");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Hello Pushkar", result);
    }

    @Test
    void greetShouldThrowAnExceptionForNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> greeting.greet(null));
    }

    @Test
    void greetShouldThrowAnExceptionForNameIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> greeting.greet(""));
    }

}
