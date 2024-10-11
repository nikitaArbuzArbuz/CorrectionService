package ru.t1.java.correctionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.dto.TransactionDto;
import ru.t1.java.correctionservice.feign.FeignClientService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionRetryService {
    private final FeignClientService feignClientService;

    @Scheduled(fixedDelayString = "${retry.interval.ms}")
    public void retryUnblockTransactions() {
        List<TransactionDto> listTransactions = feignClientService.getCanceledTransactions();
        listTransactions.forEach(feignClientService::createTransaction);
    }
}
