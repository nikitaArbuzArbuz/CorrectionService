package ru.t1.java.correctionservice.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.t1.java.correctionservice.app.domain.entity.TransactionError;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransactionErrorDto {
    private Long accountId;
    private BigDecimal amount;
    private String description;
    private TransactionError.TransactionType type;
}
