package eu.cifpfbmoll.controller;

import eu.cifpfbmoll.Fibonacci;
import eu.cifpfbmoll.model.FibonacciRecord;
import eu.cifpfbmoll.service.FibonacciRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {
    private final FibonacciRecordService fibonacciRecordService;
    private final Fibonacci fibonacci;

    public FibonacciController(FibonacciRecordService fibonacciRecordService) {
        this.fibonacciRecordService = fibonacciRecordService;
        this.fibonacci = new Fibonacci();
    }

    @GetMapping("/calculate/{n}")
    public ResponseEntity<Map<String, Object>> calculateFibonacci(@PathVariable int n) {
        try {
            if (n < 0) {
                return ResponseEntity.badRequest().body(Map.of("error", "N must be non-negative"));
            }
            int result = fibonacci.calculate(n);
            FibonacciRecord record = fibonacciRecordService.createRecord(n);
            return ResponseEntity.ok(Map.of(
                    "n", n,
                    "result", result,
                    "recordId", record.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<FibonacciRecord>> getAllRecords() {
        return ResponseEntity.ok(fibonacciRecordService.getAllRecords());
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<FibonacciRecord> getRecordById(@PathVariable Long id) {
        return fibonacciRecordService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/records")
    public ResponseEntity<FibonacciRecord> createRecord(@RequestBody Map<String, Integer> payload) {
        try {
            Integer n = payload.get("n");
            if (n == null || n < 0) {
                return ResponseEntity.badRequest().build();
            }
            FibonacciRecord record = fibonacciRecordService.createRecord(n);
            return ResponseEntity.status(HttpStatus.CREATED).body(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        try {
            fibonacciRecordService.deleteRecord(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
