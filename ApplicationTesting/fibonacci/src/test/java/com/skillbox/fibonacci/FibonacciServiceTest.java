package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FibonacciServiceTest {

    @Mock
    private FibonacciRepository repository;

    @Mock
    private FibonacciCalculator calculator;

    @InjectMocks
    private FibonacciService fibonacciService;

    public FibonacciServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Service returns Fibonacci number from DB if it exists")
    public void testReturnFibonacciNumberFromDB() {
        // Arrange
        int index = 10;
        int value = 55;
        FibonacciNumber fibonacciNumber = new FibonacciNumber(index, value);

        // Mock the repository to return the FibonacciNumber when queried
        when(repository.findByIndex(index)).thenReturn(Optional.of(fibonacciNumber));

        // Act
        FibonacciNumber result = fibonacciService.fibonacciNumber(index);

        // Assert
        assertEquals(fibonacciNumber, result);
        // Verify that repository.findByIndex was called with the correct index
        verify(repository).findByIndex(index);
        // Verify that the repository.save and calculator.getFibonacciNumber methods were not called
        verifyNoInteractions(calculator);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Service calculates and saves Fibonacci number if not in DB")
    public void testCalculateAndSaveFibonacciNumber() {
        // Arrange
        int index = 10;
        int calculatedValue = 55;
        FibonacciNumber computedNumber = new FibonacciNumber(index, calculatedValue);

        // Mock the repository to return empty when queried
        when(repository.findByIndex(index)).thenReturn(Optional.empty());
        // Mock the calculator to return the calculated Fibonacci number
        when(calculator.getFibonacciNumber(index)).thenReturn(calculatedValue);

        // Act
        FibonacciNumber result = fibonacciService.fibonacciNumber(index);

        // Assert
        assertEquals(computedNumber.getValue(), result.getValue());
        assertEquals(computedNumber.getIndex(), result.getIndex());
        // Verify that repository.findByIndex was called with the correct index
        verify(repository).findByIndex(index);
        // Verify that calculator.getFibonacciNumber was called with the correct index
        verify(calculator).getFibonacciNumber(index);
        // Verify that repository.save was called with a FibonacciNumber that has the correct index and value
        verify(repository).save(argThat(fib ->
                fib.getIndex() == index && fib.getValue() == calculatedValue
        ));
        // Verify no more interactions with mocks
        verifyNoMoreInteractions(repository, calculator);
    }
    @Test
    @DisplayName("Service should throw exception for index less than 1")
    public void testIndexLessThanOne() {
        // Arrange
        int invalidIndex = 0;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.fibonacciNumber(invalidIndex);
        });

        // Verify that no interactions occurred with the mocks
        verifyNoInteractions(repository, calculator);

        // Test for negative index as well
        int negativeIndex = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.fibonacciNumber(negativeIndex);
        });

        // Verify again that no interactions occurred with the mocks
        verifyNoInteractions(repository, calculator);
    }
}
