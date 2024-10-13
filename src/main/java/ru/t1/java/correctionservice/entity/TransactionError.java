package ru.t1.java.correctionservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_errors", schema = " t1_demo_correction")
public class TransactionError {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transact_err_seq")
    @SequenceGenerator(name = "transact_err_seq", sequenceName = "transact_err_seq", schema = "t1_demo_correction")
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_id", unique = true, nullable = false)
    private Long transactionId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    public enum TransactionType {
        ADD, SUBTRACT, CANCEL
    }

    public TransactionError setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
        return this;
    }
}
