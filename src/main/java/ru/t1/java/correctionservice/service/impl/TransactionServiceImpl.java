package ru.t1.java.correctionservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.entity.TransactionError;
import ru.t1.java.correctionservice.feign.FeignClientService;
import ru.t1.java.correctionservice.mapper.TransactionErrorMapper;
import ru.t1.java.correctionservice.repository.TransactionErrorRepository;
import ru.t1.java.correctionservice.service.TransactionService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final FeignClientService feignClientService;
    private final TransactionErrorRepository transactionErrorRepository;
    private final TransactionErrorMapper transactionErrorMapper;

    @Override
    public void unblockAccount(Long transactionId) {
        ResponseEntity<TransactionErrorDto> response = feignClientService.unblockAccount(transactionId);
        if (response.getStatusCode().is2xxSuccessful()) {
            transactionErrorRepository.deleteByTransactionId(transactionId);
            log.info("Счёт разблокирован, id транзакции: {}", transactionId);
        } else {
            TransactionError transactionError = transactionErrorMapper.map(response.getBody())
                    .setTransactionId(transactionId);
            try {
                transactionErrorRepository.saveAndFlush(transactionError);
            } catch (DataIntegrityViolationException e) {
                log.info("Transaction error with id {} already exists.", transactionId);
            }
            log.info("Информация об ошибочной транзакции записана в бд, id транзакции: {}", transactionId);
        }
    }
}
