package eu.cifpfbmoll.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fibonacci_records")
public class FibonacciRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int inputN;
    private int result;

    public FibonacciRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getInputN() { return inputN; }
    public void setInputN(int inputN) { this.inputN = inputN; }

    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }
}