package eu.cifpfbmoll.controller;

import eu.cifpfbmoll.model.FibonacciRecord;
import eu.cifpfbmoll.repository.FibonacciRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("FibonacciController Integration Tests")
public class FibonacciControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FibonacciRecordRepository fibonacciRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        fibonacciRecordRepository.deleteAll();
    }

    @Test
    @DisplayName("Should calculate Fibonacci and store result")
    void shouldCalculateFibonacci() throws Exception {
        mockMvc.perform(get("/api/fibonacci/calculate/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.n").value(5))
                .andExpect(jsonPath("$.result").value(5));
    }

    @Test
    @DisplayName("Should return error for negative Fibonacci input")
    void shouldReturnErrorForNegativeInput() throws Exception {
        mockMvc.perform(get("/api/fibonacci/calculate/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Should create Fibonacci record")
    void shouldCreateFibonacciRecord() throws Exception {
        String payload = "{\"n\": 6}";
        mockMvc.perform(post("/api/fibonacci/records")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.inputN").value(6))
                .andExpect(jsonPath("$.result").value(8));
    }

    @Test
    @DisplayName("Should get all Fibonacci records")
    void shouldGetAllRecords() throws Exception {
        FibonacciRecord record = new FibonacciRecord();
        record.setInputN(5);
        record.setResult(5);
        fibonacciRecordRepository.save(record);

        mockMvc.perform(get("/api/fibonacci/records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].inputN").value(5));
    }
}
