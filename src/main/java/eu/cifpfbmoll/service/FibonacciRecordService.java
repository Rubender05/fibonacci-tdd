package eu.cifpfbmoll.service;

import eu.cifpfbmoll.Fibonacci;
import eu.cifpfbmoll.model.FibonacciRecord;
import eu.cifpfbmoll.repository.FibonacciRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FibonacciRecordService {
    private final FibonacciRecordRepository fibonacciRecordRepository;
    private final Fibonacci fibonacci;

    public FibonacciRecordService(FibonacciRecordRepository fibonacciRecordRepository) {
        this.fibonacciRecordRepository = fibonacciRecordRepository;
        this.fibonacci = new Fibonacci();
    }

    public List<FibonacciRecord> getAllRecords() {
        return fibonacciRecordRepository.findAll();
    }

    public Optional<FibonacciRecord> getRecordById(Long id) {
        return fibonacciRecordRepository.findById(id);
    }

    public FibonacciRecord createRecord(int inputN) {
        if (inputN < 0) {
            throw new IllegalArgumentException("Input N cannot be negative");
        }
        FibonacciRecord record = new FibonacciRecord();
        record.setInputN(inputN);
        record.setResult(fibonacci.calculate(inputN));
        return fibonacciRecordRepository.save(record);
    }

    public FibonacciRecord saveRecord(FibonacciRecord record) {
        if (record.getInputN() < 0) {
            throw new IllegalArgumentException("Input N cannot be negative");
        }
        if (record.getResult() == 0) {
            record.setResult(fibonacci.calculate(record.getInputN()));
        }
        return fibonacciRecordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        if (!fibonacciRecordRepository.existsById(id)) {
            throw new IllegalArgumentException("Record not found");
        }
        fibonacciRecordRepository.deleteById(id);
    }

    public List<FibonacciRecord> findByInputN(int inputN) {
        return fibonacciRecordRepository.findByInputN(inputN);
    }
}
