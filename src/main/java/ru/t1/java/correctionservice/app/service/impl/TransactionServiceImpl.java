package ru.t1.java.correctionservice.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.adapter.feign.FeignClientService;
import ru.t1.java.correctionservice.app.service.TransactionService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final FeignClientService feignClientService;

    @Override
    public void unblockAccount(Long transactionId) {
        ResponseEntity<String> response = feignClientService.unblockAccount(transactionId);
        if (response.getStatusCode().is2xxSuccessful()) {
            feignClientService.deleteTransactionById(transactionId);
            log.info("Счёт разблокирован, id транзакции: {}", transactionId);
        } else {
            feignClientService.createTransactionById(transactionId);
            log.info("Отправлен запрос на создание новой транзакции, id транзакции: {}", transactionId);
        }
    }
}
