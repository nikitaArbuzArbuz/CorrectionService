package ru.t1.java.correctionservice.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.app.domain.entity.TransactionError;
import ru.t1.java.correctionservice.adapter.feign.FeignClientService;
import ru.t1.java.correctionservice.adapter.repository.TransactionErrorRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionRetryService {
    private final FeignClientService feignClientService;
    private final TransactionErrorRepository transactionErrorRepository;

    @Scheduled(fixedDelayString = "${retry.interval.ms}")
    public void retryUnblockTransactions() {
        transactionErrorRepository.findAll()
                .stream()
                .map(TransactionError::getTransactionId)
                .forEach(feignClientService::unblockAccount);
        log.info("Транзакции отправлены на повторную обработку");
    }
}
