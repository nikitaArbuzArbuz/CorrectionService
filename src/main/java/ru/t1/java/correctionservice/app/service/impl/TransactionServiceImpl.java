package ru.t1.java.correctionservice.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.utils.exceptions.FeignBadRequestException;
import ru.t1.java.correctionservice.app.domain.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.app.domain.entity.TransactionError;
import ru.t1.java.correctionservice.adapter.feign.FeignClientService;
import ru.t1.java.correctionservice.app.mapper.TransactionErrorMapper;
import ru.t1.java.correctionservice.adapter.repository.TransactionErrorRepository;
import ru.t1.java.correctionservice.app.service.TransactionService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final FeignClientService feignClientService;
    private final TransactionErrorRepository transactionErrorRepository;
    private final TransactionErrorMapper transactionErrorMapper;

    @Override
    public void unblockAccount(Long transactionId) {
        try {
            ResponseEntity<TransactionErrorDto> response = feignClientService.unblockAccount(transactionId);

            if (response.getStatusCode().is2xxSuccessful()) {
                transactionErrorRepository.deleteByTransactionId(transactionId);
                log.info("Счёт разблокирован, id транзакции: {}", transactionId);
            }
        } catch (FeignBadRequestException ex) {
            TransactionErrorDto errorDto = ex.getErrorDto();
            log.warn("Ошибка при разблокировке, код ответа: 400, id транзакции: {}", transactionId);

            TransactionError transactionError = transactionErrorMapper.map(errorDto)
                    .setTransactionId(transactionId);

            try {
                transactionErrorRepository.saveAndFlush(transactionError);
                log.info("Информация об ошибочной транзакции записана в БД, id транзакции: {}", transactionId);
            } catch (DataIntegrityViolationException e) {
                log.error("Ошибка при записи ошибки в БД, id транзакции: {}, ошибка: {}", transactionId, e.getMessage());
            }
        }
    }
}
