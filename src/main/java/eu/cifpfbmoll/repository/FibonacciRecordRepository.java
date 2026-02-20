package eu.cifpfbmoll.repository;

import eu.cifpfbmoll.model.FibonacciRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FibonacciRecordRepository extends JpaRepository<FibonacciRecord, Long> {
    List<FibonacciRecord> findByInputN(int inputN);
}
