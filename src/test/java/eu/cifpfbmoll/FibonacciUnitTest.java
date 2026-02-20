package eu.cifpfbmoll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Fibonacci Unit Tests")
public class FibonacciUnitTest {
    private final Fibonacci fibonacci = new Fibonacci();

    @Test
    @DisplayName("Fibonacci of 0 should return 0")
    void shouldCalculateFibonacciOfZero() {
        assertEquals(0, fibonacci.calculate(0));
    }

    @Test
    @DisplayName("Fibonacci of 1 should return 1")
    void shouldCalculateFibonacciOfOne() {
        assertEquals(1, fibonacci.calculate(1));
    }

    @Test
    @DisplayName("Fibonacci of 2 should return 1")
    void shouldCalculateFibonacciOfTwo() {
        assertEquals(1, fibonacci.calculate(2));
    }

    @Test
    @DisplayName("Fibonacci of 3 should return 2")
    void shouldCalculateFibonacciOfThree() {
        assertEquals(2, fibonacci.calculate(3));
    }

    @Test
    @DisplayName("Fibonacci of 4 should return 3")
    void shouldCalculateFibonacciOfFour() {
        assertEquals(3, fibonacci.calculate(4));
    }

    @Test
    @DisplayName("Fibonacci of 5 should return 5")
    void shouldCalculateFibonacciOfFive() {
        assertEquals(5, fibonacci.calculate(5));
    }

    @Test
    @DisplayName("Fibonacci of 6 should return 8")
    void shouldCalculateFibonacciOfSix() {
        assertEquals(8, fibonacci.calculate(6));
    }

    @Test
    @DisplayName("Fibonacci of 7 should return 13")
    void shouldCalculateFibonacciOfSeven() {
        assertEquals(13, fibonacci.calculate(7));
    }

    @Test
    @DisplayName("Fibonacci of 8 should return 21")
    void shouldCalculateFibonacciOfEight() {
        assertEquals(21, fibonacci.calculate(8));
    }

    @Test
    @DisplayName("Fibonacci of 12 should return 144")
    void shouldCalculateFibonacciOfTwelve() {
        assertEquals(144, fibonacci.calculate(12));
    }

    @Test
    @DisplayName("Fibonacci with negative number should throw exception")
    void shouldThrowExceptionForNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> fibonacci.calculate(-1));
    }

    @Test
    @DisplayName("Fibonacci result should be positive")
    void shouldReturnPositiveResultForPositiveInput() {
        assertTrue(fibonacci.calculate(5) > 0);
    }

    @Test
    @DisplayName("Fibonacci of 4 should not equal 10")
    void shouldNotEqualTen() {
        assertNotEquals(10, fibonacci.calculate(4));
    }
}
