package eu.cifpfbmoll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FibonacciTest {

    private Fibonacci fibonacci = new Fibonacci();

    // -- Tests per a la seqüència bàsica --

    @Test
    void fibonacciOfZeroIsZero() {
        assertEquals(0, fibonacci.calculate(0));
    }

    @Test
    void fibonacciOfOneIsOne() {
        assertEquals(1, fibonacci.calculate(1));
    }

    // Utilitzem ParameterizedTest per provar una sèrie de valors
    @ParameterizedTest(name = "F({0}) should be {1}")
    @CsvSource({
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21"
    })
    void fibonacciSequenceIsCorrect(int input, int expected) {
        assertEquals(expected, fibonacci.calculate(input));
    }

    // -- Tests de validació --

    @Test
    void fibonacciOfNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> fibonacci.calculate(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10, 20})
    void fibonacciAlwaysReturnsNonNegativeValue(int n) {
        assertTrue(fibonacci.calculate(n) >= 0);
    }
}