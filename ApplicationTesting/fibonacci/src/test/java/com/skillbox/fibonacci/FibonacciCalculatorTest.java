package com.skillbox.fibonacci;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FibonacciCalculatorTest
{
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 1",
            "10, 55",
            "5, 5"
    })
    @DisplayName("Test Fibonacci numbers")
    public void testFibonacciNumbers(int input, int expectedOutput)
    {
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();
        int output = fibonacciCalculator.getFibonacciNumber(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    @DisplayName("Test case for index 0")
    public void TestFibonacciIndexZero()
    {
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciCalculator.getFibonacciNumber(0);
        });
    }


}
