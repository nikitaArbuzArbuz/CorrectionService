package ru.t1.java.correctionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.java.correctionservice.entity.TransactionError;

@Repository
public interface TransactionErrorRepository extends JpaRepository<TransactionError, Integer> {
    void deleteByTransactionId(Long transactionId);
}
