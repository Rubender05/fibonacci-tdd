package eu.cifpfbmoll.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fibonacci_records")
public class FibonacciRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int inputN;
    private long result;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructores, Getters y Setters
    public FibonacciRecord() {}
    public Long getId() { return id; }
    public int getInputN() { return inputN; }
    public void setInputN(int inputN) { this.inputN = inputN; }
    public long getResult() { return result; }
    public void setResult(long result) { this.result = result; }
}