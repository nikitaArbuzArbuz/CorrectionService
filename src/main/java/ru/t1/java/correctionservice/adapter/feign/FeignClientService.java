package ru.t1.java.correctionservice.adapter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t1.java.correctionservice.config.FeignConfig;
import ru.t1.java.correctionservice.app.domain.dto.TransactionErrorDto;

@FeignClient(name = "clientService", url = "${service.client-service}", configuration = FeignConfig.class)
public interface FeignClientService {
    @PostMapping("/account/unblock/{transactionId}")
    ResponseEntity<TransactionErrorDto> unblockAccount(@PathVariable Long transactionId);
}
