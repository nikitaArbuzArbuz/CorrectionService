package ru.t1.java.correctionservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransactionDto {
    private Long accountId;
    private BigDecimal amount;
    private String description;
    private TransactionType type;

    public enum TransactionType {
        ADD, SUBTRACT, CANCEL
    }
}
