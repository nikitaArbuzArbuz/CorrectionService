package ru.t1.java.correctionservice.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.t1.java.correctionservice.adapter.feign.FeignClientService;;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionRetryService {
    private final FeignClientService feignClientService;

    @Scheduled(fixedDelayString = "${retry.interval.ms}")
    public void retryUnblockTransactions() {
        feignClientService.getCanceledTransactions()
                .forEach(feignClientService::createTransaction);
    }
}
