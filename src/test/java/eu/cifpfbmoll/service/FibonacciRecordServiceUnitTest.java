package eu.cifpfbmoll.service;

import eu.cifpfbmoll.Fibonacci;
import eu.cifpfbmoll.model.FibonacciRecord;
import eu.cifpfbmoll.repository.FibonacciRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FibonacciRecordService Unit Tests")
public class FibonacciRecordServiceUnitTest {

    @Mock
    private FibonacciRecordRepository fibonacciRecordRepository;

    @InjectMocks
    private FibonacciRecordService fibonacciRecordService;

    private FibonacciRecord testRecord;

    @BeforeEach
    void setUp() {
        testRecord = new FibonacciRecord();
        testRecord.setId(1L);
        testRecord.setInputN(5);
        testRecord.setResult(5);
    }

    @Test
    @DisplayName("Should create Fibonacci record successfully")
    void shouldCreateRecordSuccessfully() {
        when(fibonacciRecordRepository.save(any(FibonacciRecord.class))).thenReturn(testRecord);

        FibonacciRecord result = fibonacciRecordService.createRecord(5);

        assertNotNull(result);
        assertEquals(5, result.getInputN());
        assertEquals(5, result.getResult());
        verify(fibonacciRecordRepository, times(1)).save(any(FibonacciRecord.class));
    }

    @Test
    @DisplayName("Should throw exception for negative input")
    void shouldThrowExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> fibonacciRecordService.createRecord(-1));
    }

    @Test
    @DisplayName("Should get record by ID successfully")
    void shouldGetRecordByIdSuccessfully() {
        when(fibonacciRecordRepository.findById(1L)).thenReturn(Optional.of(testRecord));

        Optional<FibonacciRecord> result = fibonacciRecordService.getRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals(5, result.get().getInputN());
        verify(fibonacciRecordRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should delete record successfully")
    void shouldDeleteRecordSuccessfully() {
        when(fibonacciRecordRepository.existsById(1L)).thenReturn(true);

        fibonacciRecordService.deleteRecord(1L);

        verify(fibonacciRecordRepository, times(1)).deleteById(1L);
    }
}
