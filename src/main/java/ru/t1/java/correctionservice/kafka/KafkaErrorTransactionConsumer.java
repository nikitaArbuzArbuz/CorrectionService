package ru.t1.java.correctionservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.t1.java.correctionservice.service.TransactionService;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaErrorTransactionConsumer {
    private final TransactionService transactionService;

    @Bean
    Consumer<Long> listen() {
        return this::listener;
    }

    private void listener(Long messageId) {
        log.info("Информация о транзакции с ID {} отправлена на обработку", messageId);
        transactionService.unblockAccount(messageId);
    }
}
