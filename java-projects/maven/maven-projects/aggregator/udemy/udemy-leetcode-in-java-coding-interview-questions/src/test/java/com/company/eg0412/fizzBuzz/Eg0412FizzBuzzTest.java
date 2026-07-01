package com.company.eg0412.fizzBuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class Eg0412FizzBuzzTest {

    private final Eg0412FizzBuzz fizzBuzz = new Eg0412FizzBuzz();

    @Test
    void testFizzBuzzFor15() {
        List<String> expected = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz",
                "Fizz", "7", "8", "Fizz", "Buzz",
                "11", "Fizz", "13", "14", "FizzBuzz"
        );

        assertEquals(expected, fizzBuzz.fizzBuzz(15));
    }

    @Test
    void testFizzBuzzFor1() {
        List<String> expected = List.of("1");

        assertEquals(expected, fizzBuzz.fizzBuzz(1));
    }

    @Test
    void testFizzBuzzFor5() {
        List<String> expected = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz"
        );

        assertEquals(expected, fizzBuzz.fizzBuzz(5));
    }

    @Test
    void testFizzBuzzFor20() {
        List<String> expected = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz",
                "Fizz", "7", "8", "Fizz", "Buzz",
                "11", "Fizz", "13", "14", "FizzBuzz",
                "16", "17", "Fizz", "19", "Buzz"
        );

        assertEquals(expected, fizzBuzz.fizzBuzz(20));
    }
}