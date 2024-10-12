package ru.t1.java.correctionservice.adapter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.t1.java.correctionservice.app.domain.dto.TransactionDto;
import ru.t1.java.correctionservice.config.FeignConfig;

import java.util.List;

@FeignClient(name = "clientService", url = "${service.client-service", configuration = FeignConfig.class)
public interface FeignClientService {
    @PostMapping("/account/unblock/{transactionId}")
    ResponseEntity<String> unblockAccount(@PathVariable Long transactionId);

    @PostMapping("/transact/delete")
    void deleteTransactionById(@RequestBody Long transactionId);

    @PostMapping("/transact/new")
    void createTransaction(@RequestBody TransactionDto transactionDto);

    @PostMapping("/transact/newById")
    void createTransactionById(@RequestBody Long transactionId);

    @GetMapping("/getCanceled")
    List<TransactionDto> getCanceledTransactions();
}
