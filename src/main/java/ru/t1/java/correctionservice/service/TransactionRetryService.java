package ru.t1.java.correctionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.dto.TransactionErrorDto;
import ru.t1.java.correctionservice.entity.TransactionError;
import ru.t1.java.correctionservice.feign.FeignClientService;
import ru.t1.java.correctionservice.repository.TransactionErrorRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionRetryService {
    private final FeignClientService feignClientService;
    private final TransactionErrorRepository transactionErrorRepository;

    @Scheduled(fixedDelayString = "${retry.interval.ms}")
    public void retryUnblockTransactions() {
//        transactionErrorRepository.findAll()
//                .stream()
//                .map(TransactionError::getTransactionId)
//                .forEach(feignClientService::unblockAccount);;
    }
}
